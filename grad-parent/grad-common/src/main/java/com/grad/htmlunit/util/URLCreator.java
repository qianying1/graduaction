/*
 * Copyright (c) 2002-2015 Gargoyle Software Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.grad.htmlunit.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLStreamHandler;

import org.apache.commons.lang3.StringUtils;

import com.gargoylesoftware.htmlunit.WebClient;
import com.grad.htmlunit.gae.GAEUtils;
import com.grad.htmlunit.protocol.AnyHandler;
import com.grad.htmlunit.protocol.javascript.JavaScriptURLConnection;

/**
 * Responsible for URL creation.
 * @version $Revision: 10071 $
 * @author Marc Guillemot
 */
abstract class URLCreator {
    abstract URL toUrlUnsafeClassic(final String url) throws MalformedURLException;
    abstract String getProtocol(URL url);

    protected URL toNormalUrl(final String url) throws MalformedURLException {
        final URL response = new URL(url);
        if (response.getProtocol().startsWith("http") && StringUtils.isEmpty(response.getHost())) {
            throw new MalformedURLException("Missing host name in url: " + url);
        }
        return response;
    }

    /**
     * Gets the instance responsible for URL creating, detecting if we are running on GoogleAppEngine
     * where custom URLStreamHandler is prohibited.
     */
    static URLCreator getCreator() {
        if (!GAEUtils.isGaeMode()) {
            return new URLCreatorStandard();
        }
        return new URLCreatorGAE();
    }

    /**
     * For the normal case.
     */
    static class URLCreatorStandard extends URLCreator {
        private static final URLStreamHandler JS_HANDLER
            = new com.gargoylesoftware.htmlunit.protocol.javascript.Handler();
        private static final URLStreamHandler ABOUT_HANDLER
            = new com.gargoylesoftware.htmlunit.protocol.about.Handler();
        private static final URLStreamHandler DATA_HANDLER = new com.gargoylesoftware.htmlunit.protocol.data.Handler();

        @Override
        URL toUrlUnsafeClassic(final String url) throws MalformedURLException {
            final String protocol = org.apache.commons.lang3.StringUtils.substringBefore(url, ":").toLowerCase();

            if (protocol.isEmpty() || UrlUtils.isNormalUrlProtocol(protocol)) {
                return toNormalUrl(url);
            }
            else if (JavaScriptURLConnection.JAVASCRIPT_PREFIX.equals(protocol + ":")) {
                return new URL(null, url, JS_HANDLER);
            }
            else if ("about".equals(protocol)) {
                if (WebClient.URL_ABOUT_BLANK != null
                        && org.apache.commons.lang3.StringUtils.equalsIgnoreCase(
                                WebClient.URL_ABOUT_BLANK.toExternalForm(), url)) {
                    return WebClient.URL_ABOUT_BLANK;
                }
                return new URL(null, url, ABOUT_HANDLER);
            }
            else if ("data".equals(protocol)) {
                return new URL(null, url, DATA_HANDLER);
            }
            else  {
                return new URL(null, url, AnyHandler.INSTANCE);
            }
        }

        @Override
        String getProtocol(final URL url) {
            return url.getProtocol();
        }
    }

    /**
     * For working on GoogleAppEngine. The URL hack will require special handling from a dedicated WebConnection.
     */
    static class URLCreatorGAE extends URLCreator {
        private static final String PREFIX = "http://gaeHack_";

        @Override
        URL toUrlUnsafeClassic(final String url) throws MalformedURLException {
            if (WebClient.URL_ABOUT_BLANK != null
                    && org.apache.commons.lang3.StringUtils.equalsIgnoreCase(
                            WebClient.URL_ABOUT_BLANK.toExternalForm(), url)) {
                return WebClient.URL_ABOUT_BLANK;
            }
            else if (org.apache.commons.lang3.StringUtils.startsWithIgnoreCase(url,
                    JavaScriptURLConnection.JAVASCRIPT_PREFIX)) {
                return new URL(PREFIX + url.replaceFirst(":", "/"));
            }
            else if (org.apache.commons.lang3.StringUtils.startsWithIgnoreCase(url, "about:")) {
                return new URL(PREFIX + url.replaceFirst(":", "/"));
            }
            else if (org.apache.commons.lang3.StringUtils.startsWithIgnoreCase(url, "data:")) {
                return new URL(PREFIX + url.replaceFirst(":", "/"));
            }
            else {
                return toNormalUrl(url);
            }
        }

        @Override
        String getProtocol(final URL url) {
            final String stringUrl = url.toString();
            if (stringUrl.startsWith(PREFIX)) {
                final int begin = PREFIX.length();
                final int end = stringUrl.indexOf("/", begin);
                return stringUrl.substring(begin, end);
            }

            return url.getProtocol();
        }
    }
}

