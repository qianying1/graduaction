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

import java.util.Map;
import java.util.WeakHashMap;

import com.grad.htmlunit.core.BrowserVersion;
import com.grad.htmlunit.core.javascript.NamedNodeMap;
import com.grad.htmlunit.core.javascript.SimpleScriptable;
import com.grad.htmlunit.core.javascript.host.ActiveXObject;
import com.grad.htmlunit.core.javascript.host.ApplicationCache;
import com.grad.htmlunit.core.javascript.host.BarProp;
import com.grad.htmlunit.core.javascript.host.BatteryManager;
import com.grad.htmlunit.core.javascript.host.BroadcastChannel;
import com.grad.htmlunit.core.javascript.host.Cache;
import com.grad.htmlunit.core.javascript.host.CacheStorage;
import com.grad.htmlunit.core.javascript.host.ClientRect;
import com.grad.htmlunit.core.javascript.host.ClientRectList;
import com.grad.htmlunit.core.javascript.host.ClipboardData;
import com.grad.htmlunit.core.javascript.host.Console;
import com.grad.htmlunit.core.javascript.host.DeviceStorage;
import com.grad.htmlunit.core.javascript.host.Element;
import com.grad.htmlunit.core.javascript.host.External;
import com.grad.htmlunit.core.javascript.host.FontFace;
import com.grad.htmlunit.core.javascript.host.Gamepad;
import com.grad.htmlunit.core.javascript.host.GamepadButton;
import com.grad.htmlunit.core.javascript.host.History;
import com.grad.htmlunit.core.javascript.host.ImageBitmap;
import com.grad.htmlunit.core.javascript.host.InputMethodContext;
import com.grad.htmlunit.core.javascript.host.Location;
import com.grad.htmlunit.core.javascript.host.MessageChannel;
import com.grad.htmlunit.core.javascript.host.MessagePort;
import com.grad.htmlunit.core.javascript.host.MimeType;
import com.grad.htmlunit.core.javascript.host.MimeTypeArray;
import com.grad.htmlunit.core.javascript.host.Namespace;
import com.grad.htmlunit.core.javascript.host.NamespaceCollection;
import com.grad.htmlunit.core.javascript.host.Navigator;
import com.grad.htmlunit.core.javascript.host.Notification;
import com.grad.htmlunit.core.javascript.host.PermissionStatus;
import com.grad.htmlunit.core.javascript.host.Permissions;
import com.grad.htmlunit.core.javascript.host.Plugin;
import com.grad.htmlunit.core.javascript.host.PluginArray;
import com.grad.htmlunit.core.javascript.host.Popup;
import com.grad.htmlunit.core.javascript.host.Promise;
import com.grad.htmlunit.core.javascript.host.Proxy;
import com.grad.htmlunit.core.javascript.host.PushManager;
import com.grad.htmlunit.core.javascript.host.PushSubscription;
import com.grad.htmlunit.core.javascript.host.ReadableByteStream;
import com.grad.htmlunit.core.javascript.host.ReadableStream;
import com.grad.htmlunit.core.javascript.host.Screen;
import com.grad.htmlunit.core.javascript.host.ScreenOrientation;
import com.grad.htmlunit.core.javascript.host.Set;
import com.grad.htmlunit.core.javascript.host.SharedWorker;
import com.grad.htmlunit.core.javascript.host.SimpleArray;
import com.grad.htmlunit.core.javascript.host.Storage;
import com.grad.htmlunit.core.javascript.host.Symbol;
import com.grad.htmlunit.core.javascript.host.TextDecoder;
import com.grad.htmlunit.core.javascript.host.TextEncoder;
import com.grad.htmlunit.core.javascript.host.Touch;
import com.grad.htmlunit.core.javascript.host.TouchList;
import com.grad.htmlunit.core.javascript.host.URL;
import com.grad.htmlunit.core.javascript.host.URLSearchParams;
import com.grad.htmlunit.core.javascript.host.WeakMap;
import com.grad.htmlunit.core.javascript.host.WeakSet;
import com.grad.htmlunit.core.javascript.host.WebSocket;
import com.grad.htmlunit.core.javascript.host.Window;
import com.grad.htmlunit.core.javascript.host.XPathExpression;
import com.grad.htmlunit.core.javascript.host.arrays.ArrayBuffer;
import com.grad.htmlunit.core.javascript.host.arrays.ArrayBufferView;
import com.grad.htmlunit.core.javascript.host.arrays.ArrayBufferViewBase;
import com.grad.htmlunit.core.javascript.host.arrays.DataView;
import com.grad.htmlunit.core.javascript.host.arrays.Float32Array;
import com.grad.htmlunit.core.javascript.host.arrays.Float64Array;
import com.grad.htmlunit.core.javascript.host.arrays.Int16Array;
import com.grad.htmlunit.core.javascript.host.arrays.Int32Array;
import com.grad.htmlunit.core.javascript.host.arrays.Int8Array;
import com.grad.htmlunit.core.javascript.host.arrays.Uint16Array;
import com.grad.htmlunit.core.javascript.host.arrays.Uint32Array;
import com.grad.htmlunit.core.javascript.host.arrays.Uint8Array;
import com.grad.htmlunit.core.javascript.host.arrays.Uint8ClampedArray;
import com.grad.htmlunit.core.javascript.host.canvas.CanvasGradient;
import com.grad.htmlunit.core.javascript.host.canvas.CanvasPattern;
import com.grad.htmlunit.core.javascript.host.canvas.CanvasRenderingContext2D;
import com.grad.htmlunit.core.javascript.host.canvas.ImageData;
import com.grad.htmlunit.core.javascript.host.canvas.Path2D;
import com.grad.htmlunit.core.javascript.host.canvas.TextMetrics;
import com.grad.htmlunit.core.javascript.host.canvas.WebGLActiveInfo;
import com.grad.htmlunit.core.javascript.host.canvas.WebGLBuffer;
import com.grad.htmlunit.core.javascript.host.canvas.WebGLFramebuffer;
import com.grad.htmlunit.core.javascript.host.canvas.WebGLProgram;
import com.grad.htmlunit.core.javascript.host.canvas.WebGLRenderbuffer;
import com.grad.htmlunit.core.javascript.host.canvas.WebGLRenderingContext;
import com.grad.htmlunit.core.javascript.host.canvas.WebGLShader;
import com.grad.htmlunit.core.javascript.host.canvas.WebGLShaderPrecisionFormat;
import com.grad.htmlunit.core.javascript.host.canvas.WebGLTexture;
import com.grad.htmlunit.core.javascript.host.canvas.WebGLUniformLocation;
import com.grad.htmlunit.core.javascript.host.crypto.Crypto;
import com.grad.htmlunit.core.javascript.host.crypto.CryptoKey;
import com.grad.htmlunit.core.javascript.host.crypto.SubtleCrypto;
import com.grad.htmlunit.core.javascript.host.css.CSS;
import com.grad.htmlunit.core.javascript.host.css.CSS2Properties;
import com.grad.htmlunit.core.javascript.host.css.CSSCharsetRule;
import com.grad.htmlunit.core.javascript.host.css.CSSConditionRule;
import com.grad.htmlunit.core.javascript.host.css.CSSCounterStyleRule;
import com.grad.htmlunit.core.javascript.host.css.CSSFontFaceRule;
import com.grad.htmlunit.core.javascript.host.css.CSSGroupingRule;
import com.grad.htmlunit.core.javascript.host.css.CSSImportRule;
import com.grad.htmlunit.core.javascript.host.css.CSSKeyframeRule;
import com.grad.htmlunit.core.javascript.host.css.CSSKeyframesRule;
import com.grad.htmlunit.core.javascript.host.css.CSSMediaRule;
import com.grad.htmlunit.core.javascript.host.css.CSSNamespaceRule;
import com.grad.htmlunit.core.javascript.host.css.CSSPageRule;
import com.grad.htmlunit.core.javascript.host.css.CSSPrimitiveValue;
import com.grad.htmlunit.core.javascript.host.css.CSSRule;
import com.grad.htmlunit.core.javascript.host.css.CSSRuleList;
import com.grad.htmlunit.core.javascript.host.css.CSSStyleDeclaration;
import com.grad.htmlunit.core.javascript.host.css.CSSStyleRule;
import com.grad.htmlunit.core.javascript.host.css.CSSStyleSheet;
import com.grad.htmlunit.core.javascript.host.css.CSSSupportsRule;
import com.grad.htmlunit.core.javascript.host.css.CSSUnknownRule;
import com.grad.htmlunit.core.javascript.host.css.CSSValue;
import com.grad.htmlunit.core.javascript.host.css.CSSViewportRule;
import com.grad.htmlunit.core.javascript.host.css.CaretPosition;
import com.grad.htmlunit.core.javascript.host.css.ComputedCSSStyleDeclaration;
import com.grad.htmlunit.core.javascript.host.css.MediaQueryList;
import com.grad.htmlunit.core.javascript.host.css.StyleMedia;
import com.grad.htmlunit.core.javascript.host.css.StyleSheet;
import com.grad.htmlunit.core.javascript.host.css.StyleSheetList;
import com.grad.htmlunit.core.javascript.host.css.WebKitCSSMatrix;
import com.grad.htmlunit.core.javascript.host.dom.AbstractList;
import com.grad.htmlunit.core.javascript.host.dom.Attr;
import com.grad.htmlunit.core.javascript.host.dom.CDATASection;
import com.grad.htmlunit.core.javascript.host.dom.CharacterData;
import com.grad.htmlunit.core.javascript.host.dom.Comment;
import com.grad.htmlunit.core.javascript.host.dom.DOMCursor;
import com.grad.htmlunit.core.javascript.host.dom.DOMError;
import com.grad.htmlunit.core.javascript.host.dom.DOMException;
import com.grad.htmlunit.core.javascript.host.dom.DOMImplementation;
import com.grad.htmlunit.core.javascript.host.dom.DOMMatrix;
import com.grad.htmlunit.core.javascript.host.dom.DOMMatrixReadOnly;
import com.grad.htmlunit.core.javascript.host.dom.DOMParser;
import com.grad.htmlunit.core.javascript.host.dom.DOMPoint;
import com.grad.htmlunit.core.javascript.host.dom.DOMPointReadOnly;
import com.grad.htmlunit.core.javascript.host.dom.DOMRectReadOnly;
import com.grad.htmlunit.core.javascript.host.dom.DOMRequest;
import com.grad.htmlunit.core.javascript.host.dom.DOMSettableTokenList;
import com.grad.htmlunit.core.javascript.host.dom.DOMStringList;
import com.grad.htmlunit.core.javascript.host.dom.DOMStringMap;
import com.grad.htmlunit.core.javascript.host.dom.DOMTokenList;
import com.grad.htmlunit.core.javascript.host.dom.Document;
import com.grad.htmlunit.core.javascript.host.dom.DocumentFragment;
import com.grad.htmlunit.core.javascript.host.dom.DocumentType;
import com.grad.htmlunit.core.javascript.host.dom.EventNode;
import com.grad.htmlunit.core.javascript.host.dom.MediaList;
import com.grad.htmlunit.core.javascript.host.dom.MutationObserver;
import com.grad.htmlunit.core.javascript.host.dom.MutationRecord;
import com.grad.htmlunit.core.javascript.host.dom.Node;
import com.grad.htmlunit.core.javascript.host.dom.NodeFilter;
import com.grad.htmlunit.core.javascript.host.dom.NodeIterator;
import com.grad.htmlunit.core.javascript.host.dom.NodeList;
import com.grad.htmlunit.core.javascript.host.dom.ProcessingInstruction;
import com.grad.htmlunit.core.javascript.host.dom.RadioNodeList;
import com.grad.htmlunit.core.javascript.host.dom.Range;
import com.grad.htmlunit.core.javascript.host.dom.Selection;
import com.grad.htmlunit.core.javascript.host.dom.ShadowRoot;
import com.grad.htmlunit.core.javascript.host.dom.StaticNodeList;
import com.grad.htmlunit.core.javascript.host.dom.Text;
import com.grad.htmlunit.core.javascript.host.dom.TextRange;
import com.grad.htmlunit.core.javascript.host.dom.TreeWalker;
import com.grad.htmlunit.core.javascript.host.dom.XPathEvaluator;
import com.grad.htmlunit.core.javascript.host.dom.XPathNSResolver;
import com.grad.htmlunit.core.javascript.host.dom.XPathResult;
import com.grad.htmlunit.core.javascript.host.event.AnimationEvent;
import com.grad.htmlunit.core.javascript.host.event.ApplicationCacheErrorEvent;
import com.grad.htmlunit.core.javascript.host.event.AudioProcessingEvent;
import com.grad.htmlunit.core.javascript.host.event.AutocompleteErrorEvent;
import com.grad.htmlunit.core.javascript.host.event.BeforeUnloadEvent;
import com.grad.htmlunit.core.javascript.host.event.BlobEvent;
import com.grad.htmlunit.core.javascript.host.event.ClipboardEvent;
import com.grad.htmlunit.core.javascript.host.event.CloseEvent;
import com.grad.htmlunit.core.javascript.host.event.CompositionEvent;
import com.grad.htmlunit.core.javascript.host.event.CustomEvent;
import com.grad.htmlunit.core.javascript.host.event.DeviceLightEvent;
import com.grad.htmlunit.core.javascript.host.event.DeviceMotionEvent;
import com.grad.htmlunit.core.javascript.host.event.DeviceOrientationEvent;
import com.grad.htmlunit.core.javascript.host.event.DeviceProximityEvent;
import com.grad.htmlunit.core.javascript.host.event.DeviceStorageChangeEvent;
import com.grad.htmlunit.core.javascript.host.event.DragEvent;
import com.grad.htmlunit.core.javascript.host.event.ErrorEvent;
import com.grad.htmlunit.core.javascript.host.event.Event;
import com.grad.htmlunit.core.javascript.host.event.EventSource;
import com.grad.htmlunit.core.javascript.host.event.EventTarget;
import com.grad.htmlunit.core.javascript.host.event.FocusEvent;
import com.grad.htmlunit.core.javascript.host.event.GamepadEvent;
import com.grad.htmlunit.core.javascript.host.event.HashChangeEvent;
import com.grad.htmlunit.core.javascript.host.event.IDBVersionChangeEvent;
import com.grad.htmlunit.core.javascript.host.event.InputEvent;
import com.grad.htmlunit.core.javascript.host.event.KeyboardEvent;
import com.grad.htmlunit.core.javascript.host.event.MIDIConnectionEvent;
import com.grad.htmlunit.core.javascript.host.event.MIDIMessageEvent;
import com.grad.htmlunit.core.javascript.host.event.MediaEncryptedEvent;
import com.grad.htmlunit.core.javascript.host.event.MediaKeyEvent;
import com.grad.htmlunit.core.javascript.host.event.MediaKeyMessageEvent;
import com.grad.htmlunit.core.javascript.host.event.MediaQueryListEvent;
import com.grad.htmlunit.core.javascript.host.event.MediaStreamEvent;
import com.grad.htmlunit.core.javascript.host.event.MessageEvent;
import com.grad.htmlunit.core.javascript.host.event.MouseEvent;
import com.grad.htmlunit.core.javascript.host.event.MouseScrollEvent;
import com.grad.htmlunit.core.javascript.host.event.MouseWheelEvent;
import com.grad.htmlunit.core.javascript.host.event.MozContactChangeEvent;
import com.grad.htmlunit.core.javascript.host.event.MozMmsEvent;
import com.grad.htmlunit.core.javascript.host.event.MozSettingsEvent;
import com.grad.htmlunit.core.javascript.host.event.MozSmsEvent;
import com.grad.htmlunit.core.javascript.host.event.MutationEvent;
import com.grad.htmlunit.core.javascript.host.event.OfflineAudioCompletionEvent;
import com.grad.htmlunit.core.javascript.host.event.PageTransitionEvent;
import com.grad.htmlunit.core.javascript.host.event.PointerEvent;
import com.grad.htmlunit.core.javascript.host.event.PopStateEvent;
import com.grad.htmlunit.core.javascript.host.event.ProgressEvent;
import com.grad.htmlunit.core.javascript.host.event.RTCDataChannelEvent;
import com.grad.htmlunit.core.javascript.host.event.RTCPeerConnectionIceEvent;
import com.grad.htmlunit.core.javascript.host.event.SVGZoomEvent;
import com.grad.htmlunit.core.javascript.host.event.SecurityPolicyViolationEvent;
import com.grad.htmlunit.core.javascript.host.event.SpeechSynthesisEvent;
import com.grad.htmlunit.core.javascript.host.event.StorageEvent;
import com.grad.htmlunit.core.javascript.host.event.TextEvent;
import com.grad.htmlunit.core.javascript.host.event.TimeEvent;
import com.grad.htmlunit.core.javascript.host.event.TouchEvent;
import com.grad.htmlunit.core.javascript.host.event.TrackEvent;
import com.grad.htmlunit.core.javascript.host.event.TransitionEvent;
import com.grad.htmlunit.core.javascript.host.event.UIEvent;
import com.grad.htmlunit.core.javascript.host.event.UserProximityEvent;
import com.grad.htmlunit.core.javascript.host.event.WebGLContextEvent;
import com.grad.htmlunit.core.javascript.host.event.WebKitTransitionEvent;
import com.grad.htmlunit.core.javascript.host.event.WheelEvent;
import com.grad.htmlunit.core.javascript.host.event.XMLHttpRequestProgressEvent;
import com.grad.htmlunit.core.javascript.host.fetch.Headers;
import com.grad.htmlunit.core.javascript.host.fetch.Request;
import com.grad.htmlunit.core.javascript.host.fetch.Response;
import com.grad.htmlunit.core.javascript.host.file.Blob;
import com.grad.htmlunit.core.javascript.host.file.DataTransferItem;
import com.grad.htmlunit.core.javascript.host.file.DataTransferItemList;
import com.grad.htmlunit.core.javascript.host.file.File;
import com.grad.htmlunit.core.javascript.host.file.FileError;
import com.grad.htmlunit.core.javascript.host.file.FileHandle;
import com.grad.htmlunit.core.javascript.host.file.FileList;
import com.grad.htmlunit.core.javascript.host.file.FileReader;
import com.grad.htmlunit.core.javascript.host.file.FileRequest;
import com.grad.htmlunit.core.javascript.host.file.LockedFile;
import com.grad.htmlunit.core.javascript.host.geo.Coordinates;
import com.grad.htmlunit.core.javascript.host.geo.Geolocation;
import com.grad.htmlunit.core.javascript.host.geo.Position;
import com.grad.htmlunit.core.javascript.host.geo.PositionError;
import com.grad.htmlunit.core.javascript.host.html.*;
import com.grad.htmlunit.core.javascript.host.idb.IDBCursor;
import com.grad.htmlunit.core.javascript.host.idb.IDBCursorWithValue;
import com.grad.htmlunit.core.javascript.host.idb.IDBDatabase;
import com.grad.htmlunit.core.javascript.host.idb.IDBFactory;
import com.grad.htmlunit.core.javascript.host.idb.IDBIndex;
import com.grad.htmlunit.core.javascript.host.idb.IDBKeyRange;
import com.grad.htmlunit.core.javascript.host.idb.IDBObjectStore;
import com.grad.htmlunit.core.javascript.host.idb.IDBOpenDBRequest;
import com.grad.htmlunit.core.javascript.host.idb.IDBRequest;
import com.grad.htmlunit.core.javascript.host.idb.IDBTransaction;
import com.grad.htmlunit.core.javascript.host.media.AnalyserNode;
import com.grad.htmlunit.core.javascript.host.media.AudioBuffer;
import com.grad.htmlunit.core.javascript.host.media.AudioBufferSourceNode;
import com.grad.htmlunit.core.javascript.host.media.AudioContext;
import com.grad.htmlunit.core.javascript.host.media.AudioDestinationNode;
import com.grad.htmlunit.core.javascript.host.media.AudioListener;
import com.grad.htmlunit.core.javascript.host.media.AudioNode;
import com.grad.htmlunit.core.javascript.host.media.AudioParam;
import com.grad.htmlunit.core.javascript.host.media.BiquadFilterNode;
import com.grad.htmlunit.core.javascript.host.media.ChannelMergerNode;
import com.grad.htmlunit.core.javascript.host.media.ChannelSplitterNode;
import com.grad.htmlunit.core.javascript.host.media.ConvolverNode;
import com.grad.htmlunit.core.javascript.host.media.DelayNode;
import com.grad.htmlunit.core.javascript.host.media.DynamicsCompressorNode;
import com.grad.htmlunit.core.javascript.host.media.GainNode;
import com.grad.htmlunit.core.javascript.host.media.LocalMediaStream;
import com.grad.htmlunit.core.javascript.host.media.MediaDevices;
import com.grad.htmlunit.core.javascript.host.media.MediaElementAudioSourceNode;
import com.grad.htmlunit.core.javascript.host.media.MediaError;
import com.grad.htmlunit.core.javascript.host.media.MediaKeyError;
import com.grad.htmlunit.core.javascript.host.media.MediaKeySession;
import com.grad.htmlunit.core.javascript.host.media.MediaKeyStatusMap;
import com.grad.htmlunit.core.javascript.host.media.MediaKeySystemAccess;
import com.grad.htmlunit.core.javascript.host.media.MediaKeys;
import com.grad.htmlunit.core.javascript.host.media.MediaRecorder;
import com.grad.htmlunit.core.javascript.host.media.MediaSource;
import com.grad.htmlunit.core.javascript.host.media.MediaStream;
import com.grad.htmlunit.core.javascript.host.media.MediaStreamAudioDestinationNode;
import com.grad.htmlunit.core.javascript.host.media.MediaStreamAudioSourceNode;
import com.grad.htmlunit.core.javascript.host.media.MediaStreamTrack;
import com.grad.htmlunit.core.javascript.host.media.OfflineAudioContext;
import com.grad.htmlunit.core.javascript.host.media.OscillatorNode;
import com.grad.htmlunit.core.javascript.host.media.PannerNode;
import com.grad.htmlunit.core.javascript.host.media.PeriodicWave;
import com.grad.htmlunit.core.javascript.host.media.ScriptProcessorNode;
import com.grad.htmlunit.core.javascript.host.media.StereoPannerNode;
import com.grad.htmlunit.core.javascript.host.media.TextTrack;
import com.grad.htmlunit.core.javascript.host.media.TextTrackCue;
import com.grad.htmlunit.core.javascript.host.media.TextTrackCueList;
import com.grad.htmlunit.core.javascript.host.media.TextTrackList;
import com.grad.htmlunit.core.javascript.host.media.TimeRanges;
import com.grad.htmlunit.core.javascript.host.media.VTTCue;
import com.grad.htmlunit.core.javascript.host.media.WaveShaperNode;
import com.grad.htmlunit.core.javascript.host.media.midi.MIDIAccess;
import com.grad.htmlunit.core.javascript.host.media.midi.MIDIInput;
import com.grad.htmlunit.core.javascript.host.media.midi.MIDIInputMap;
import com.grad.htmlunit.core.javascript.host.media.midi.MIDIOutput;
import com.grad.htmlunit.core.javascript.host.media.midi.MIDIOutputMap;
import com.grad.htmlunit.core.javascript.host.media.midi.MIDIPort;
import com.grad.htmlunit.core.javascript.host.media.rtc.RTCIceCandidate;
import com.grad.htmlunit.core.javascript.host.media.rtc.RTCSessionDescription;
import com.grad.htmlunit.core.javascript.host.media.rtc.mozRTCIceCandidate;
import com.grad.htmlunit.core.javascript.host.media.rtc.mozRTCPeerConnection;
import com.grad.htmlunit.core.javascript.host.media.rtc.mozRTCSessionDescription;
import com.grad.htmlunit.core.javascript.host.media.rtc.webkitRTCPeerConnection;
import com.grad.htmlunit.core.javascript.host.moz.MozMmsMessage;
import com.grad.htmlunit.core.javascript.host.moz.MozMobileMessageManager;
import com.grad.htmlunit.core.javascript.host.moz.MozMobileMessageThread;
import com.grad.htmlunit.core.javascript.host.moz.MozSmsFilter;
import com.grad.htmlunit.core.javascript.host.moz.MozSmsMessage;
import com.grad.htmlunit.core.javascript.host.moz.MozSmsSegmentInfo;
import com.grad.htmlunit.core.javascript.host.performance.Performance;
import com.grad.htmlunit.core.javascript.host.performance.PerformanceEntry;
import com.grad.htmlunit.core.javascript.host.performance.PerformanceMark;
import com.grad.htmlunit.core.javascript.host.performance.PerformanceMeasure;
import com.grad.htmlunit.core.javascript.host.performance.PerformanceNavigation;
import com.grad.htmlunit.core.javascript.host.performance.PerformanceResourceTiming;
import com.grad.htmlunit.core.javascript.host.performance.PerformanceTiming;
import com.grad.htmlunit.core.javascript.host.speech.SpeechSynthesis;
import com.grad.htmlunit.core.javascript.host.speech.SpeechSynthesisUtterance;
import com.grad.htmlunit.core.javascript.host.speech.webkitSpeechRecognition;
import com.grad.htmlunit.core.javascript.host.svg.*;
import com.grad.htmlunit.core.javascript.host.worker.ServiceWorker;
import com.grad.htmlunit.core.javascript.host.worker.ServiceWorkerContainer;
import com.grad.htmlunit.core.javascript.host.worker.ServiceWorkerRegistration;
import com.grad.htmlunit.core.javascript.host.worker.Worker;
import com.grad.htmlunit.core.javascript.host.xml.FormData;
import com.grad.htmlunit.core.javascript.host.xml.XDomainRequest;
import com.grad.htmlunit.core.javascript.host.xml.XMLDocument;
import com.grad.htmlunit.core.javascript.host.xml.XMLHttpRequest;
import com.grad.htmlunit.core.javascript.host.xml.XMLHttpRequestEventTarget;
import com.grad.htmlunit.core.javascript.host.xml.XMLHttpRequestUpload;
import com.grad.htmlunit.core.javascript.host.xml.XMLSerializer;
import com.grad.htmlunit.core.javascript.host.xml.XSLTProcessor;
import com.grad.htmlunit.core.javascript.host.xml.XSLTemplate;

/**
 * A container for all the JavaScript configuration information.
 *
 * @version $Revision: 10639 $
 * @author <a href="mailto:mbowler@GargoyleSoftware.com">Mike Bowler</a>
 * @author Chris Erskine
 * @author Ahmed Ashour
 * @author Ronald Brill
 * @author Frank Danek
 */
public final class JavaScriptConfiguration extends AbstractJavaScriptConfiguration {

	@SuppressWarnings("unchecked")
	static final Class<? extends SimpleScriptable>[] CLASSES_ = new Class[] { AbstractList.class, ActiveXObject.class,
			AnalyserNode.class, AnimationEvent.class, ApplicationCache.class, ApplicationCacheErrorEvent.class,
			ArrayBuffer.class, ArrayBufferView.class, ArrayBufferViewBase.class, Attr.class, AudioBuffer.class,
			AudioBufferSourceNode.class, AudioContext.class, AudioDestinationNode.class, AudioListener.class,
			AudioNode.class, AudioParam.class, AudioProcessingEvent.class, AutocompleteErrorEvent.class, BarProp.class,
			BatteryManager.class, BeforeUnloadEvent.class, BiquadFilterNode.class, Blob.class, BlobEvent.class,
			BroadcastChannel.class, Cache.class, CacheStorage.class, CanvasGradient.class, CanvasPattern.class,
			CanvasRenderingContext2D.class, CaretPosition.class, CDATASection.class, ChannelMergerNode.class,
			ChannelSplitterNode.class, CharacterData.class, ClientRect.class, ClientRectList.class, ClipboardData.class,
			ClipboardEvent.class, CloseEvent.class, Comment.class, CompositionEvent.class,
			ComputedCSSStyleDeclaration.class, Console.class, ConvolverNode.class, Coordinates.class, Crypto.class,
			CryptoKey.class, CSS.class, CSS2Properties.class, CSSCharsetRule.class, CSSConditionRule.class,
			CSSCounterStyleRule.class, CSSFontFaceRule.class, CSSGroupingRule.class, CSSImportRule.class,
			CSSKeyframeRule.class, CSSKeyframesRule.class, CSSMediaRule.class, CSSNamespaceRule.class,
			CSSPageRule.class, CSSPrimitiveValue.class, CSSRule.class, CSSRuleList.class, CSSStyleDeclaration.class,
			CSSStyleRule.class, CSSStyleSheet.class, CSSSupportsRule.class, CSSUnknownRule.class, CSSValue.class,
			CSSViewportRule.class, CustomEvent.class, DataTransfer.class, DataTransferItem.class,
			DataTransferItemList.class, DataView.class, DelayNode.class, DeviceLightEvent.class,
			DeviceMotionEvent.class, DeviceOrientationEvent.class, DeviceProximityEvent.class, DeviceStorage.class,
			DeviceStorageChangeEvent.class, Document.class, DocumentFragment.class, DocumentType.class, DOMCursor.class,
			DOMError.class, DOMException.class, DOMImplementation.class, DOMMatrix.class, DOMMatrixReadOnly.class,
			DOMParser.class, DOMPoint.class, DOMPointReadOnly.class, DOMRectReadOnly.class, DOMRequest.class,
			DOMSettableTokenList.class, DOMStringList.class, DOMStringMap.class, DOMTokenList.class, DragEvent.class,
			DynamicsCompressorNode.class, Element.class, Enumerator.class, ErrorEvent.class, Event.class,
			EventNode.class, EventSource.class, EventTarget.class, External.class, File.class, FileError.class,
			FileHandle.class, FileList.class, FileReader.class, FileRequest.class, Float32Array.class,
			Float64Array.class, FocusEvent.class, FontFace.class, FormChild.class, FormData.class, FormField.class,
			GainNode.class, Gamepad.class, GamepadButton.class, GamepadEvent.class, Geolocation.class,
			HashChangeEvent.class, Headers.class, History.class, HTMLAllCollection.class, HTMLAnchorElement.class,
			HTMLAppletElement.class, HTMLAreaElement.class, HTMLAudioElement.class, HTMLBaseElement.class,
			HTMLBaseFontElement.class, HTMLBGSoundElement.class, HTMLBlockElement.class, HTMLBodyElement.class,
			HTMLBRElement.class, HTMLButtonElement.class, HTMLCanvasElement.class, HTMLCollection.class,
			HTMLCommentElement.class, HTMLContentElement.class, HTMLDataElement.class, HTMLDataListElement.class,
			HTMLDDElement.class, HTMLDetailsElement.class, HTMLDialogElement.class, HTMLDirectoryElement.class,
			HTMLDivElement.class, HTMLDListElement.class, HTMLDocument.class, HTMLDTElement.class, HTMLElement.class,
			HTMLEmbedElement.class, HTMLFieldSetElement.class, HTMLFontElement.class, HTMLFormControlsCollection.class,
			HTMLFormElement.class, HTMLFrameElement.class, HTMLFrameSetElement.class, HTMLHeadElement.class,
			HTMLHeadingElement.class, HTMLHRElement.class, HTMLHtmlElement.class, HTMLIFrameElement.class,
			HTMLImageElement.class, HTMLInlineQuotationElement.class, HTMLInputElement.class, HTMLIsIndexElement.class,
			HTMLKeygenElement.class, HTMLLabelElement.class, HTMLLegendElement.class, HTMLLIElement.class,
			HTMLLinkElement.class, HTMLListElement.class, HTMLMapElement.class, HTMLMarqueeElement.class,
			HTMLMediaElement.class, HTMLMenuElement.class, HTMLMenuItemElement.class, HTMLMetaElement.class,
			HTMLMeterElement.class, HTMLModElement.class, HTMLNextIdElement.class, HTMLNoShowElement.class,
			HTMLObjectElement.class, HTMLOListElement.class, HTMLOptGroupElement.class, HTMLOptionElement.class,
			HTMLOptionsCollection.class, HTMLOutputElement.class, HTMLParagraphElement.class, HTMLParamElement.class,
			HTMLPhraseElement.class, HTMLPictureElement.class, HTMLPreElement.class, HTMLProgressElement.class,
			HTMLQuoteElement.class, HTMLScriptElement.class, HTMLSelectElement.class, HTMLShadowElement.class,
			HTMLSourceElement.class, HTMLSpanElement.class, HTMLStyleElement.class, HTMLTableCaptionElement.class,
			HTMLTableCellElement.class, HTMLTableColElement.class, HTMLTableComponent.class,
			HTMLTableDataCellElement.class, HTMLTableElement.class, HTMLTableHeaderCellElement.class,
			HTMLTableRowElement.class, HTMLTableSectionElement.class, HTMLTemplateElement.class,
			HTMLTextAreaElement.class, HTMLTextElement.class, HTMLTimeElement.class, HTMLTitleElement.class,
			HTMLTrackElement.class, HTMLUListElement.class, HTMLUnknownElement.class, HTMLVideoElement.class,
			IDBCursor.class, IDBCursorWithValue.class, IDBDatabase.class, IDBFactory.class, IDBIndex.class,
			IDBKeyRange.class, IDBObjectStore.class, IDBOpenDBRequest.class, IDBRequest.class, IDBTransaction.class,
			IDBVersionChangeEvent.class, Image.class, ImageBitmap.class, ImageData.class, InputEvent.class,
			InputMethodContext.class, Int16Array.class, Int32Array.class, Int8Array.class, KeyboardEvent.class,
			LocalMediaStream.class, Location.class, LockedFile.class, com.com.grad.htmlunit.core.javascript.host.Map.class,
			MediaDevices.class, MediaElementAudioSourceNode.class, MediaEncryptedEvent.class, MediaError.class,
			MediaKeyError.class, MediaKeyEvent.class, MediaKeyMessageEvent.class, MediaKeys.class,
			MediaKeySession.class, MediaKeyStatusMap.class, MediaKeySystemAccess.class, MediaList.class,
			MediaQueryList.class, MediaQueryListEvent.class, MediaRecorder.class, MediaSource.class, MediaStream.class,
			MediaStreamAudioDestinationNode.class, MediaStreamAudioSourceNode.class, MediaStreamEvent.class,
			MediaStreamTrack.class, MessageChannel.class, MessageEvent.class, MessagePort.class, MIDIAccess.class,
			MIDIConnectionEvent.class, MIDIInput.class, MIDIInputMap.class, MIDIMessageEvent.class, MIDIOutput.class,
			MIDIOutputMap.class, MIDIPort.class, MimeType.class, MimeTypeArray.class, MouseEvent.class,
			MouseScrollEvent.class, MouseWheelEvent.class, MozContactChangeEvent.class, MozMmsEvent.class,
			MozMmsMessage.class, MozMobileMessageManager.class, MozMobileMessageThread.class, mozRTCIceCandidate.class,
			mozRTCPeerConnection.class, mozRTCSessionDescription.class, MozSettingsEvent.class, MozSmsEvent.class,
			MozSmsFilter.class, MozSmsMessage.class, MozSmsSegmentInfo.class, MutationEvent.class,
			MutationObserver.class, MutationRecord.class, NamedNodeMap.class, Namespace.class,
			NamespaceCollection.class, Navigator.class, Node.class, NodeFilter.class, NodeIterator.class,
			NodeList.class, Notification.class, OfflineAudioCompletionEvent.class, OfflineAudioContext.class,
			Option.class, OscillatorNode.class, PageTransitionEvent.class, PannerNode.class, Path2D.class,
			Performance.class, PerformanceEntry.class, PerformanceMark.class, PerformanceMeasure.class,
			PerformanceNavigation.class, PerformanceResourceTiming.class, PerformanceTiming.class, PeriodicWave.class,
			Permissions.class, PermissionStatus.class, Plugin.class, PluginArray.class, PointerEvent.class,
			PopStateEvent.class, Popup.class, Position.class, PositionError.class, ProcessingInstruction.class,
			ProgressEvent.class, Promise.class, Proxy.class, PushManager.class, PushSubscription.class,
			RadioNodeList.class, Range.class, ReadableByteStream.class, ReadableStream.class, Request.class,
			Response.class, RowContainer.class, RTCDataChannelEvent.class, RTCIceCandidate.class,
			RTCPeerConnectionIceEvent.class, RTCSessionDescription.class, Screen.class, ScreenOrientation.class,
			ScriptProcessorNode.class, SecurityPolicyViolationEvent.class, Selection.class, ServiceWorker.class,
			ServiceWorkerContainer.class, ServiceWorkerRegistration.class, Set.class, ShadowRoot.class,
			SharedWorker.class, SimpleArray.class, SpeechSynthesis.class, SpeechSynthesisEvent.class,
			SpeechSynthesisUtterance.class, StaticNodeList.class, StereoPannerNode.class, Storage.class,
			StorageEvent.class, StyleMedia.class, StyleSheet.class, StyleSheetList.class, SubtleCrypto.class,
			SVGAElement.class, SVGAltGlyphElement.class, SVGAngle.class, SVGAnimatedAngle.class,
			SVGAnimatedBoolean.class, SVGAnimatedEnumeration.class, SVGAnimatedInteger.class, SVGAnimatedLength.class,
			SVGAnimatedLengthList.class, SVGAnimatedNumber.class, SVGAnimatedNumberList.class,
			SVGAnimatedPreserveAspectRatio.class, SVGAnimatedRect.class, SVGAnimatedString.class,
			SVGAnimatedTransformList.class, SVGAnimateElement.class, SVGAnimateMotionElement.class,
			SVGAnimateTransformElement.class, SVGAnimationElement.class, SVGCircleElement.class,
			SVGClipPathElement.class, SVGComponentTransferFunctionElement.class, SVGCursorElement.class,
			SVGDefsElement.class, SVGDescElement.class, SVGDiscardElement.class, SVGDocument.class, SVGElement.class,
			SVGEllipseElement.class, SVGFEBlendElement.class, SVGFEColorMatrixElement.class,
			SVGFEComponentTransferElement.class, SVGFECompositeElement.class, SVGFEConvolveMatrixElement.class,
			SVGFEDiffuseLightingElement.class, SVGFEDisplacementMapElement.class, SVGFEDistantLightElement.class,
			SVGFEDropShadowElement.class, SVGFEFloodElement.class, SVGFEFuncAElement.class, SVGFEFuncBElement.class,
			SVGFEFuncGElement.class, SVGFEFuncRElement.class, SVGFEGaussianBlurElement.class, SVGFEImageElement.class,
			SVGFEMergeElement.class, SVGFEMergeNodeElement.class, SVGFEMorphologyElement.class,
			SVGFEOffsetElement.class, SVGFEPointLightElement.class, SVGFESpecularLightingElement.class,
			SVGFESpotLightElement.class, SVGFETileElement.class, SVGFETurbulenceElement.class, SVGFilterElement.class,
			SVGForeignObjectElement.class, SVGGElement.class, SVGGeometryElement.class, SVGGradientElement.class,
			SVGGraphicsElement.class, SVGImageElement.class, SVGLength.class, SVGLengthList.class,
			SVGLinearGradientElement.class, SVGLineElement.class, SVGMarkerElement.class, SVGMaskElement.class,
			SVGMatrix.class, SVGMetadataElement.class, SVGMPathElement.class, SVGNumber.class, SVGNumberList.class,
			SVGPathElement.class, SVGPathSeg.class, SVGPathSegArcAbs.class, SVGPathSegArcRel.class,
			SVGPathSegClosePath.class, SVGPathSegCurvetoCubicAbs.class, SVGPathSegCurvetoCubicRel.class,
			SVGPathSegCurvetoCubicSmoothAbs.class, SVGPathSegCurvetoCubicSmoothRel.class,
			SVGPathSegCurvetoQuadraticAbs.class, SVGPathSegCurvetoQuadraticRel.class,
			SVGPathSegCurvetoQuadraticSmoothAbs.class, SVGPathSegCurvetoQuadraticSmoothRel.class,
			SVGPathSegLinetoAbs.class, SVGPathSegLinetoHorizontalAbs.class, SVGPathSegLinetoHorizontalRel.class,
			SVGPathSegLinetoRel.class, SVGPathSegLinetoVerticalAbs.class, SVGPathSegLinetoVerticalRel.class,
			SVGPathSegList.class, SVGPathSegMovetoAbs.class, SVGPathSegMovetoRel.class, SVGPatternElement.class,
			SVGPoint.class, SVGPointList.class, SVGPolygonElement.class, SVGPolylineElement.class,
			SVGPreserveAspectRatio.class, SVGRadialGradientElement.class, SVGRect.class, SVGRectElement.class,
			SVGRenderingIntent.class, SVGScriptElement.class, SVGSetElement.class, SVGStopElement.class,
			SVGStringList.class, SVGStyleElement.class, SVGSVGElement.class, SVGSwitchElement.class,
			SVGSymbolElement.class, SVGTextContentElement.class, SVGTextElement.class, SVGTextPathElement.class,
			SVGTextPositioningElement.class, SVGTitleElement.class, SVGTransform.class, SVGTransformList.class,
			SVGTSpanElement.class, SVGUnitTypes.class, SVGUseElement.class, SVGViewElement.class, SVGViewSpec.class,
			SVGZoomEvent.class, Symbol.class, Text.class, TextDecoder.class, TextEncoder.class, TextEvent.class,
			TextMetrics.class, TextRange.class, TextTrack.class, TextTrackCue.class, TextTrackCueList.class,
			TextTrackList.class, TimeEvent.class, TimeRanges.class, Touch.class, TouchEvent.class, TouchList.class,
			TrackEvent.class, TransitionEvent.class, TreeWalker.class, UIEvent.class, Uint16Array.class,
			Uint32Array.class, Uint8Array.class, Uint8ClampedArray.class, URL.class, URLSearchParams.class,
			UserProximityEvent.class, ValidityState.class, VTTCue.class, WaveShaperNode.class, WeakMap.class,
			WeakSet.class, WebGLActiveInfo.class, WebGLBuffer.class, WebGLContextEvent.class, WebGLFramebuffer.class,
			WebGLProgram.class, WebGLRenderbuffer.class, WebGLRenderingContext.class, WebGLShader.class,
			WebGLShaderPrecisionFormat.class, WebGLTexture.class, WebGLUniformLocation.class, WebKitCSSMatrix.class,
			webkitRTCPeerConnection.class, webkitSpeechRecognition.class, WebKitTransitionEvent.class, WebSocket.class,
			WheelEvent.class, Window.class, Worker.class, XDomainRequest.class, XMLDocument.class, XMLHttpRequest.class,
			XMLHttpRequestEventTarget.class, XMLHttpRequestProgressEvent.class, XMLHttpRequestUpload.class,
			XMLSerializer.class, XPathEvaluator.class, XPathExpression.class, XPathNSResolver.class, XPathResult.class,
			XSLTemplate.class, XSLTProcessor.class };

	/**
	 * Cache of browser versions and their corresponding JavaScript
	 * configurations.
	 */
	private static final Map<BrowserVersion, JavaScriptConfiguration> CONFIGURATION_MAP_ = new WeakHashMap<>();

	/**
	 * Constructor is only called from {@link #getInstance(BrowserVersion)}
	 * which is synchronized.
	 * 
	 * @param browser
	 *            the browser version to use
	 */
	protected JavaScriptConfiguration(final BrowserVersion browser) {
		super(browser);
	}

	/**
	 * Returns the instance that represents the configuration for the specified
	 * {@link BrowserVersion}. This method is synchronized to allow
	 * multi-threaded access to the JavaScript configuration.
	 * 
	 * @param browserVersion
	 *            the {@link BrowserVersion}
	 * @return the instance for the specified {@link BrowserVersion}
	 */
	public static synchronized JavaScriptConfiguration getInstance(final BrowserVersion browserVersion) {
		if (browserVersion == null) {
			throw new IllegalStateException("BrowserVersion must be defined");
		}
		JavaScriptConfiguration configuration = CONFIGURATION_MAP_.get(browserVersion);

		if (configuration == null) {
			configuration = new JavaScriptConfiguration(browserVersion);
			CONFIGURATION_MAP_.put(browserVersion, configuration);
		}
		return configuration;
	}

	@Override
	protected Class<? extends SimpleScriptable>[] getClasses() {
		return CLASSES_;
	}
}
