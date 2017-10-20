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

import static com.grad.htmlunit.BrowserVersionFeatures.JS_NOTIFICATION_GRANTED;
import static com.grad.htmlunit.core.javascript1.configuration.BrowserName.CHROME;
import static com.grad.htmlunit.core.javascript1.configuration.BrowserName.FF;

import com.grad.htmlunit.core.javascript1.SimpleScriptable;
import com.grad.htmlunit.core.javascript1.configuration.JsxClass;
import com.grad.htmlunit.core.javascript1.configuration.JsxConstructor;
import com.grad.htmlunit.core.javascript1.configuration.JsxStaticFunction;
import com.grad.htmlunit.core.javascript1.configuration.JsxStaticGetter;
import com.grad.htmlunit.core.javascript1.configuration.WebBrowser;
import com.grad.htmlunit.core.javascript1.host.event.EventTarget;

import net.sourceforge.htmlunit.corejs.javascript.Scriptable;

/**
 * A Notification.
 *
 * @see <a href="https://developer.mozilla.org/en/docs/Web/API/notification">
 * MDN - Notification</a>
 * @version $Revision: 10780 $
 * @author Marc Guillemot
 * @author Ronald Brill
 * @author Ahmed Ashour
 */
@JsxClass(browsers = { @WebBrowser(CHROME), @WebBrowser(FF) })
public class Notification extends EventTarget {

    /**
     * JavaScript constructor.
     * @param title the title
     */
    @JsxConstructor
    public void jsConstructor(final String title) {
        // Empty.
    }

    /**
     * Returns the {@code permission} static property.
     * @param thisObj the scriptable
     * @return the {@code permission} static property
     */
    @JsxStaticGetter
    public static String getPermission(final Scriptable thisObj) {
        final SimpleScriptable scripatble = (SimpleScriptable) thisObj.getParentScope();
        if (scripatble.getBrowserVersion().hasFeature(JS_NOTIFICATION_GRANTED)) {
            return "granted";
        }
        return "default";
    }

    /**
     * Asks the user for permission.
     */
    @JsxStaticFunction
    public static void requestPermission() {
        // TODO
    }
}
