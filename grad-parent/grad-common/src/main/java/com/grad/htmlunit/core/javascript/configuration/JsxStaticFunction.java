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
package com.grad.htmlunit.core.javascript.configuration;

import static com.grad.htmlunit.core.javascript.configuration.BrowserName.CHROME;
import static com.grad.htmlunit.core.javascript.configuration.BrowserName.FF;
import static com.grad.htmlunit.core.javascript.configuration.BrowserName.IE;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation to mark a Java method as JavaScript function, not at instance level.
 *
 * @version $Revision: 10322 $
 * @author Ahmed Ashour
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface JsxStaticFunction {

    /** The {@link WebBrowser}s supported by this function. */
    WebBrowser[] value() default {
        @WebBrowser(IE),
        @WebBrowser(FF),
        @WebBrowser(CHROME)
    };
}

