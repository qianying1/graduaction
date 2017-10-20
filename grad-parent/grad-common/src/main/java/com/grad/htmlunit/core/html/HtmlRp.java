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
package com.grad.htmlunit.core.html;

import static com.grad.htmlunit.BrowserVersionFeatures.CSS_RT_DISPLAY_INLINE;
import static com.grad.htmlunit.BrowserVersionFeatures.CSS_RT_DISPLAY_RUBY_TEXT_ALWAYS;

import java.util.Map;

import com.grad.htmlunit.SgmlPage;

/**
 * Wrapper for the HTML element "rp".
 *
 * @version $Revision: 10803 $
 * @author Ronald Brill
 * @author Frank Danek
 * @author Ahmed Ashour
 */
public class HtmlRp extends HtmlElement {

    /** The HTML tag represented by this element. */
    public static final String TAG_NAME = "rp";
    private boolean createdByJavascript_;

    /**
     * Creates a new instance.
     *
     * @param qualifiedName the qualified name of the element type to instantiate
     * @param page the page that contains this element
     * @param attributes the initial attributes
     */
    HtmlRp(final String qualifiedName, final SgmlPage page,
            final Map<String, DomAttr> attributes) {
        super(qualifiedName, page, attributes);
    }

    /**
     * <span style="color:red">INTERNAL API - SUBJECT TO CHANGE AT ANY TIME - USE AT YOUR OWN RISK.</span><br>
     *
     * Marks this frame as created by javascript. This is needed to handle
     * some special IE behavior.
     */
    public void markAsCreatedByJavascript() {
        createdByJavascript_ = true;
    }

    /**
     * <span style="color:red">INTERNAL API - SUBJECT TO CHANGE AT ANY TIME - USE AT YOUR OWN RISK.</span><br>
     *
     * Returns true if this frame was created by javascript. This is needed to handle
     * some special IE behavior.
     * @return true or false
     */
    public boolean wasCreatedByJavascript() {
        return createdByJavascript_;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DisplayStyle getDefaultStyleDisplay() {
        if (hasFeature(CSS_RT_DISPLAY_RUBY_TEXT_ALWAYS)) {
            return DisplayStyle.INLINE;
        }
        if (wasCreatedByJavascript()) {
            if (getParentNode() == null) {
                return DisplayStyle.EMPTY;
            }
        }
        else {
            if (!hasFeature(CSS_RT_DISPLAY_INLINE)) {
                return DisplayStyle.NONE;
            }
        }
        return DisplayStyle.INLINE;
    }
}
