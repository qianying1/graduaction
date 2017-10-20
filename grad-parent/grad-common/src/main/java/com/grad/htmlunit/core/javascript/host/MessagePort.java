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
package com.grad.htmlunit.core.javascript.host;

import static com.grad.htmlunit.BrowserVersionFeatures.JS_WINDOW_POST_MESSAGE_SYNCHRONOUS;
import static com.grad.htmlunit.core.javascript1.configuration.BrowserName.CHROME;
import static com.grad.htmlunit.core.javascript1.configuration.BrowserName.FF;
import static com.grad.htmlunit.core.javascript1.configuration.BrowserName.IE;

import java.net.URL;

import com.grad.htmlunit.core.javascript1.JavaScriptEngine;
import com.grad.htmlunit.core.javascript1.PostponedAction;
import com.grad.htmlunit.core.javascript1.configuration.JsxClass;
import com.grad.htmlunit.core.javascript1.configuration.JsxConstructor;
import com.grad.htmlunit.core.javascript1.configuration.JsxFunction;
import com.grad.htmlunit.core.javascript1.configuration.JsxGetter;
import com.grad.htmlunit.core.javascript1.configuration.JsxSetter;
import com.grad.htmlunit.core.javascript1.configuration.WebBrowser;
import com.grad.htmlunit.core.javascript1.host.event.Event;
import com.grad.htmlunit.core.javascript1.host.event.EventTarget;
import com.grad.htmlunit.core.javascript1.host.event.MessageEvent;

import net.sourceforge.htmlunit.corejs.javascript.Context;
import net.sourceforge.htmlunit.corejs.javascript.ContextAction;
import net.sourceforge.htmlunit.corejs.javascript.ContextFactory;
import net.sourceforge.htmlunit.corejs.javascript.Function;

/**
 * A JavaScript object for MessagePort.
 *
 * @version $Revision: 10780 $
 * @author Ahmed Ashour
 */
@JsxClass(browsers = { @WebBrowser(CHROME), @WebBrowser(FF), @WebBrowser(value = IE, minVersion = 11) })
public class MessagePort extends EventTarget {

    private MessagePort port1_;

    /**
     * Default constructor.
     */
    @JsxConstructor({ @WebBrowser(CHROME), @WebBrowser(FF) })
    public MessagePort() {
    }

    /**
     * Constructors {@code port2} with the specified {@code port1}.
     * @param port1 the port1
     */
    public MessagePort(final MessagePort port1) {
        port1_ = port1;
    }

    /**
     * Returns the value of the window's {@code onmessage} property.
     * @return the value of the window's {@code onmessage} property
     */
    @JsxGetter
    public Object getOnmessage() {
        return getHandlerForJavaScript(Event.TYPE_MESSAGE);
    }

    /**
     * Sets the value of the window's {@code onmessage} property.
     * @param onmessage the value of the window's {@code onmessage} property
     */
    @JsxSetter
    public void setOnmessage(final Object onmessage) {
        setHandlerForJavaScript(Event.TYPE_MESSAGE, onmessage);
    }

    private Object getHandlerForJavaScript(final String eventName) {
        return getEventListenersContainer().getEventHandlerProp(eventName);
    }

    private void setHandlerForJavaScript(final String eventName, final Object handler) {
        if (handler == null || handler instanceof Function) {
            getEventListenersContainer().setEventHandlerProp(eventName, handler);
        }
        // Otherwise, fail silently.
    }

    /**
     * Posts a message.
     * @param message the object passed to the window
     * @param transfer an optional sequence of Transferable objects
     * @see <a href="https://developer.mozilla.org/en-US/docs/Web/API/window.postMessage">MDN documentation</a>
     */
    @JsxFunction
    public void postMessage(final String message, final Object transfer) {
        if (port1_ != null) {
            final URL currentURL = getWindow().getWebWindow().getEnclosedPage().getUrl();
            final MessageEvent event = new MessageEvent();
            final String origin = currentURL.getProtocol() + "://" + currentURL.getHost() + ':' + currentURL.getPort();
            event.initMessageEvent(Event.TYPE_MESSAGE, false, false, message, origin, "", getWindow(), transfer);
            event.setParentScope(port1_);
            event.setPrototype(getPrototype(event.getClass()));

            if (getBrowserVersion().hasFeature(JS_WINDOW_POST_MESSAGE_SYNCHRONOUS)) {
                port1_.dispatchEvent(event);
                return;
            }

            final JavaScriptEngine jsEngine = getWindow().getWebWindow().getWebClient().getJavaScriptEngine();
            final PostponedAction action = new PostponedAction(getWindow().getWebWindow().getEnclosedPage()) {
                @Override
                public void execute() throws Exception {
                    final ContextAction action = new ContextAction() {
                        @Override
                        public Object run(final Context cx) {
                            return port1_.dispatchEvent(event);
                        }
                    };

                    final ContextFactory cf = jsEngine.getContextFactory();
                    cf.call(action);
                }
            };
            jsEngine.addPostponedAction(action);
        }
    }

}
