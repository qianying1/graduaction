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

import static com.grad.htmlunit.core.javascript1.configuration.BrowserName.CHROME;

import com.grad.htmlunit.core.javascript1.SimpleScriptable;
import com.grad.htmlunit.core.javascript1.configuration.JsxClass;
import com.grad.htmlunit.core.javascript1.configuration.JsxConstructor;
import com.grad.htmlunit.core.javascript1.configuration.WebBrowser;

/**
 * A JavaScript object for {@code CacheStorage}.
 *
 * @version $Revision: 10573 $
 * @author Ahmed Ashour
 */
@JsxClass(browsers = @WebBrowser(CHROME))
public class CacheStorage extends SimpleScriptable {

    /**
     * Creates a new instance.
     */
    @JsxConstructor
    public CacheStorage() {
    }
}