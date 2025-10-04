(function (factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', './kotlin-kotlin-stdlib.js'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('./kotlin-kotlin-stdlib.js'));
  else {
    if (typeof globalThis['kotlin-kotlin-stdlib'] === 'undefined') {
      throw new Error("Error loading module 'vision.gears:client'. Its dependency 'kotlin-kotlin-stdlib' was not found. Please, check whether 'kotlin-kotlin-stdlib' is loaded prior to 'vision.gears:client'.");
    }
    globalThis['vision.gears:client'] = factory(typeof globalThis['vision.gears:client'] === 'undefined' ? {} : globalThis['vision.gears:client'], globalThis['kotlin-kotlin-stdlib']);
  }
}(function (_, kotlin_kotlin) {
  'use strict';
  //region block: imports
  var imul = Math.imul;
  var protoOf = kotlin_kotlin.$_$.z;
  var initMetadataForClass = kotlin_kotlin.$_$.t;
  var numberToInt = kotlin_kotlin.$_$.x;
  var Unit_instance = kotlin_kotlin.$_$.l;
  var HashSet_init_$Create$ = kotlin_kotlin.$_$.g;
  var Error_init_$Create$ = kotlin_kotlin.$_$.i;
  var THROW_CCE = kotlin_kotlin.$_$.d1;
  var Error_0 = kotlin_kotlin.$_$.c1;
  var initMetadataForObject = kotlin_kotlin.$_$.w;
  var VOID = kotlin_kotlin.$_$.d;
  var KProperty1 = kotlin_kotlin.$_$.a1;
  var getPropertyCallableRef = kotlin_kotlin.$_$.s;
  var initMetadataForCompanion = kotlin_kotlin.$_$.u;
  var equals = kotlin_kotlin.$_$.r;
  var ensureNotNull = kotlin_kotlin.$_$.e1;
  var _Char___init__impl__6a9atx = kotlin_kotlin.$_$.j;
  var toString = kotlin_kotlin.$_$.k;
  var Regex_init_$Create$ = kotlin_kotlin.$_$.h;
  var objectCreate = kotlin_kotlin.$_$.y;
  var getOrNull = kotlin_kotlin.$_$.o;
  var getKClass = kotlin_kotlin.$_$.c;
  var to = kotlin_kotlin.$_$.f1;
  var mapOf = kotlin_kotlin.$_$.q;
  var HashMap_init_$Create$ = kotlin_kotlin.$_$.f;
  var split = kotlin_kotlin.$_$.b1;
  var lastOrNull = kotlin_kotlin.$_$.p;
  var dropLast = kotlin_kotlin.$_$.n;
  var ArrayList_init_$Create$ = kotlin_kotlin.$_$.e;
  var getKClassFromExpression = kotlin_kotlin.$_$.b;
  var initMetadataForInterface = kotlin_kotlin.$_$.v;
  var addAll = kotlin_kotlin.$_$.m;
  var arrayConcat = kotlin_kotlin.$_$.a;
  //endregion
  //region block: pre-declaration
  initMetadataForClass(App$gl$1);
  initMetadataForClass(App, 'App');
  initMetadataForObject(keyNames, 'keyNames');
  initMetadataForClass(Drawable, 'Drawable');
  initMetadataForClass(UniformProvider, 'UniformProvider', VOID, Drawable);
  initMetadataForClass(Material, 'Material', VOID, UniformProvider);
  initMetadataForClass(Mesh, 'Mesh', VOID, UniformProvider);
  initMetadataForClass(OrthoCamera, 'OrthoCamera', OrthoCamera, UniformProvider);
  initMetadataForCompanion(Companion);
  initMetadataForClass(Program, 'Program', VOID, UniformProvider);
  initMetadataForClass(Geometry, 'Geometry', VOID, Drawable);
  initMetadataForClass(QuadGeometry, 'QuadGeometry', VOID, Geometry);
  initMetadataForClass(Scene, 'Scene');
  initMetadataForClass(Shader, 'Shader');
  initMetadataForClass(TriangleGeometry, 'TriangleGeometry', VOID, Geometry);
  initMetadataForCompanion(Companion_0);
  function commit$default(gl, uniformLocation, samplerIndex, $super) {
    samplerIndex = samplerIndex === VOID ? 0 : samplerIndex;
    var tmp;
    if ($super === VOID) {
      this.ic(gl, uniformLocation, samplerIndex);
      tmp = Unit_instance;
    } else {
      tmp = $super.ic.call(this, gl, uniformLocation, samplerIndex);
    }
    return tmp;
  }
  initMetadataForInterface(Uniform, 'Uniform');
  function getStorageSize() {
    return this.hc().length;
  }
  function set(values) {
    if (values.length === 0) {
      var inductionVariable = 0;
      var last = this.hc().length;
      if (inductionVariable < last)
        do {
          var i = inductionVariable;
          inductionVariable = inductionVariable + 1 | 0;
          // Inline function 'org.khronos.webgl.set' call
          // Inline function 'kotlin.js.asDynamic' call
          this.hc()[i] = 0;
        }
         while (inductionVariable < last);
    } else {
      throw Error_init_$Create$('Cannot set float values to an int uniform.');
    }
    return this;
  }
  initMetadataForInterface(UniformInt, 'UniformInt', VOID, VOID, [Uniform]);
  initMetadataForClass(IVec1, 'IVec1', IVec1_init_$Create$, VOID, [UniformInt]);
  initMetadataForClass(IVecArray, 'IVecArray', VOID, VOID, [UniformInt]);
  initMetadataForClass(IVec1Array, 'IVec1Array', VOID, IVecArray);
  initMetadataForCompanion(Companion_1);
  initMetadataForClass(IVec2, 'IVec2', IVec2_init_$Create$, VOID, [UniformInt]);
  initMetadataForCompanion(Companion_2);
  initMetadataForClass(IVec3, 'IVec3', IVec3_init_$Create$, VOID, [UniformInt]);
  initMetadataForCompanion(Companion_3);
  initMetadataForClass(IVec4, 'IVec4', IVec4_init_$Create$, VOID, [UniformInt]);
  function getStorageSize_0() {
    return this.hc().length;
  }
  initMetadataForInterface(UniformFloat, 'UniformFloat', VOID, VOID, [Uniform]);
  initMetadataForClass(Mat4, 'Mat4', VOID, VOID, [UniformFloat]);
  initMetadataForClass(Mat4Array, 'Mat4Array', VOID, VOID, [UniformFloat]);
  initMetadataForClass(UniformDescriptor, 'UniformDescriptor');
  initMetadataForCompanion(Companion_4);
  initMetadataForClass(ProgramReflection, 'ProgramReflection', VOID, Drawable);
  function getStorageSize_1() {
    return this.hc().length;
  }
  function set_0(values) {
    if (values.length === 0) {
      throw Error_init_$Create$('Cannot set no value to a texture uniform.');
    } else {
      throw Error_init_$Create$('Cannot set float values to a texture uniform.');
    }
  }
  initMetadataForInterface(UniformSampler, 'UniformSampler', VOID, VOID, [Uniform]);
  initMetadataForClass(Sampler2D, 'Sampler2D', Sampler2D, VOID, [UniformSampler]);
  initMetadataForClass(Sampler3D, 'Sampler3D', Sampler3D, VOID, [UniformSampler]);
  initMetadataForClass(SamplerCube, 'SamplerCube', SamplerCube, VOID, [UniformSampler]);
  initMetadataForCompanion(Companion_5);
  initMetadataForClass(Vec1, 'Vec1', Vec1_init_$Create$, VOID, [UniformFloat]);
  initMetadataForClass(VecArray, 'VecArray', VOID, VOID, [UniformFloat]);
  initMetadataForClass(Vec1Array, 'Vec1Array', VOID, VecArray);
  initMetadataForCompanion(Companion_6);
  initMetadataForClass(Vec2, 'Vec2', Vec2_init_$Create$, VOID, [UniformFloat]);
  initMetadataForClass(Vec2Array, 'Vec2Array', VOID, VecArray);
  initMetadataForCompanion(Companion_7);
  initMetadataForClass(Vec3, 'Vec3', Vec3_init_$Create$, VOID, [UniformFloat]);
  initMetadataForClass(Vec3Array, 'Vec3Array', VOID, VecArray);
  initMetadataForCompanion(Companion_8);
  initMetadataForClass(Vec4, 'Vec4', Vec4_init_$Create$, VOID, [UniformFloat]);
  initMetadataForClass(Vec4Array, 'Vec4Array', VOID, VecArray);
  //endregion
  function App$gl$1() {
    this.v8_1 = false;
  }
  function App$registerEventHandlers$lambda(this$0) {
    return function (event) {
      return this$0.y8_1.d(keyNames_getInstance().j(event.keyCode));
    };
  }
  function App$registerEventHandlers$lambda_0(this$0) {
    return function (event) {
      return this$0.y8_1.f3(keyNames_getInstance().j(event.keyCode));
    };
  }
  function App$registerEventHandlers$lambda_1(event) {
    console.log('Hello World! ' + numberToInt(event.x) + ' ' + numberToInt(event.y));
    return event;
  }
  function App$registerEventHandlers$lambda_2(event) {
    event.stopPropagation();
    return Unit_instance;
  }
  function App$registerEventHandlers$lambda_3(event) {
    return event;
  }
  function App$registerEventHandlers$lambda_4(event) {
    return event;
  }
  function App$registerEventHandlers$lambda_5(this$0) {
    return function (it) {
      this$0.c9();
      return Unit_instance;
    };
  }
  function App$update$lambda(this$0) {
    return function (it) {
      this$0.c9();
      return Unit_instance;
    };
  }
  function App(canvas, overlay) {
    this.w8_1 = canvas;
    this.x8_1 = overlay;
    this.y8_1 = HashSet_init_$Create$();
    var tmp = this;
    var tmp0_elvis_lhs = this.w8_1.getContext('webgl2', new App$gl$1());
    var tmp_0;
    if (tmp0_elvis_lhs == null) {
      throw Error_init_$Create$('Browser does not support WebGL2');
    } else {
      tmp_0 = tmp0_elvis_lhs;
    }
    var tmp_1 = tmp_0;
    tmp.z8_1 = tmp_1 instanceof WebGL2RenderingContext ? tmp_1 : THROW_CCE();
    this.a9_1 = new Scene(this.z8_1);
    this.d9();
  }
  protoOf(App).d9 = function () {
    this.w8_1.width = this.w8_1.clientWidth;
    this.w8_1.height = this.w8_1.clientHeight;
    this.a9_1.y9(this.w8_1);
  };
  protoOf(App).z9 = function () {
    var tmp = document;
    tmp.onkeydown = App$registerEventHandlers$lambda(this);
    var tmp_0 = document;
    tmp_0.onkeyup = App$registerEventHandlers$lambda_0(this);
    this.w8_1.onmousedown = App$registerEventHandlers$lambda_1;
    this.w8_1.onmousemove = App$registerEventHandlers$lambda_2;
    this.w8_1.onmouseup = App$registerEventHandlers$lambda_3;
    this.w8_1.onmouseout = App$registerEventHandlers$lambda_4;
    var tmp_1 = window;
    tmp_1.requestAnimationFrame(App$registerEventHandlers$lambda_5(this));
  };
  protoOf(App).c9 = function () {
    this.a9_1.aa(this.y8_1);
    var tmp = window;
    tmp.requestAnimationFrame(App$update$lambda(this));
  };
  function main() {
    var tmp = document.getElementById('canvas');
    var canvas = tmp instanceof HTMLCanvasElement ? tmp : THROW_CCE();
    var tmp_0 = document.getElementById('overlay');
    var overlay = tmp_0 instanceof HTMLDivElement ? tmp_0 : THROW_CCE();
    overlay.innerHTML = '<font color="red">WebGL<\/font>';
    try {
      var app = new App(canvas, overlay);
      app.z9();
    } catch ($p) {
      if ($p instanceof Error_0) {
        var e = $p;
        console.error(e.message);
      } else {
        throw $p;
      }
    }
  }
  function mainWrapper() {
    main();
  }
  function keyNames() {
    keyNames_instance = this;
    var tmp = this;
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    tmp.b9_1 = ['', '', '', 'CANCEL', '', '', 'HELP', '', 'BACK_SPACE', 'TAB', '', '', 'CLEAR', 'ENTER', 'ENTER_SPECIAL', '', 'SHIFT', 'CONTROL', 'ALT', 'PAUSE', 'CAPS_LOCK', 'KANA', 'EISU', 'JUNJA', 'FINAL', 'HANJA', '', 'ESCAPE', 'CONVERT', 'NONCONVERT', 'ACCEPT', 'MODECHANGE', 'SPACE', 'PAGE_UP', 'PAGE_DOWN', 'END', 'HOME', 'LEFT', 'UP', 'RIGHT', 'DOWN', 'SELECT', 'PRINT', 'EXECUTE', 'PRINTSCREEN', 'INSERT', 'DELETE', '', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'COLON', 'SEMICOLON', 'LESS_THAN', 'EQUALS', 'GREATER_THAN', 'QUESTION_MARK', 'AT', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'OS_KEY', '', 'CONTEXT_MENU', '', 'SLEEP', 'NUMPAD0', 'NUMPAD1', 'NUMPAD2', 'NUMPAD3', 'NUMPAD4', 'NUMPAD5', 'NUMPAD6', 'NUMPAD7', 'NUMPAD8', 'NUMPAD9', 'MULTIPLY', 'ADD', 'SEPARATOR', 'SUBTRACT', 'DECIMAL', 'DIVIDE', 'F1', 'F2', 'F3', 'F4', 'F5', 'F6', 'F7', 'F8', 'F9', 'F10', 'F11', 'F12', 'F13', 'F14', 'F15', 'F16', 'F17', 'F18', 'F19', 'F20', 'F21', 'F22', 'F23', 'F24', '', '', '', '', '', '', '', '', 'NUM_LOCK', 'SCROLL_LOCK', 'WIN_OEM_FJ_JISHO', 'WIN_OEM_FJ_MASSHOU', 'WIN_OEM_FJ_TOUROKU', 'WIN_OEM_FJ_LOYA', 'WIN_OEM_FJ_ROYA', '', '', '', '', '', '', '', '', '', 'CIRCUMFLEX', 'EXCLAMATION', 'DOUBLE_QUOTE', 'HASH', 'DOLLAR', 'PERCENT', 'AMPERSAND', 'UNDERSCORE', 'OPEN_PAREN', 'CLOSE_PAREN', 'ASTERISK', 'PLUS', 'PIPE', 'HYPHEN_MINUS', 'OPEN_CURLY_BRACKET', 'CLOSE_CURLY_BRACKET', 'TILDE', '', '', '', '', 'VOLUME_MUTE', 'VOLUME_DOWN', 'VOLUME_UP', '', '', 'SEMICOLON', 'EQUALS', 'COMMA', 'MINUS', 'PERIOD', 'SLASH', 'BACK_QUOTE', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', 'OPEN_BRACKET', 'BACK_SLASH', 'CLOSE_BRACKET', 'QUOTE', '', 'META', 'ALTGR', '', 'WIN_ICO_HELP', 'WIN_ICO_00', '', 'WIN_ICO_CLEAR', '', '', 'WIN_OEM_RESET', 'WIN_OEM_JUMP', 'WIN_OEM_PA1', 'WIN_OEM_PA2', 'WIN_OEM_PA3', 'WIN_OEM_WSCTRL', 'WIN_OEM_CUSEL', 'WIN_OEM_ATTN', 'WIN_OEM_FINISH', 'WIN_OEM_COPY', 'WIN_OEM_AUTO', 'WIN_OEM_ENLW', 'WIN_OEM_BACKTAB', 'ATTN', 'CRSEL', 'EXSEL', 'EREOF', 'PLAY', 'ZOOM', '', 'PA1', 'WIN_OEM_CLEAR', ''];
  }
  protoOf(keyNames).j = function (index) {
    return this.b9_1[index];
  };
  var keyNames_instance;
  function keyNames_getInstance() {
    if (keyNames_instance == null)
      new keyNames();
    return keyNames_instance;
  }
  function Material(program) {
    UniformProvider.call(this, ['material']);
    this.ea([program]);
  }
  function Mesh(material, geometry) {
    UniformProvider.call(this, ['mesh']);
    this.ea([material, geometry]);
  }
  function OrthoCamera$_get_viewProjMatrix_$ref_gtk4aq() {
    return function (p0) {
      return p0.pa();
    };
  }
  function OrthoCamera$_get_viewProjMatrix_$ref_gtk4aq_0() {
    return function (p0) {
      return p0.pa();
    };
  }
  function OrthoCamera() {
    UniformProvider.call(this, ['camera']);
    this.la_1 = Vec2_init_$Create$(0.0, 0.0);
    this.ma_1 = 0.0;
    this.na_1 = Vec2_init_$Create$(2.0, 2.0);
    var tmp = this;
    var tmp_0 = Mat4_init_$Create$(new Float32Array([]));
    var tmp_1 = KProperty1;
    tmp.oa_1 = tmp_0.ra(this, getPropertyCallableRef('viewProjMatrix', 1, tmp_1, OrthoCamera$_get_viewProjMatrix_$ref_gtk4aq(), null));
    this.sa();
  }
  protoOf(OrthoCamera).pa = function () {
    var tmp = KProperty1;
    return this.oa_1.ta(this, getPropertyCallableRef('viewProjMatrix', 1, tmp, OrthoCamera$_get_viewProjMatrix_$ref_gtk4aq_0(), null));
  };
  protoOf(OrthoCamera).sa = function () {
    this.pa().ua(new Float32Array([])).va(0.5, 0.5).wa(this.na_1).xa(this.ma_1).ya(this.la_1).za();
  };
  protoOf(OrthoCamera).ab = function (ar) {
    var tmp0 = this.na_1;
    // Inline function 'vision.gears.webglmath.Vec2.y' call
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    // Inline function 'vision.gears.webglmath.Vec2.x' call
    var value = this.na_1.bb_1[1] * ar;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    tmp0.bb_1[0] = value;
    this.sa();
  };
  function Companion() {
    Companion_instance = this;
    var tmp = this;
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    tmp.cb_1 = ['vertexPosition', 'vertexColor'];
    var tmp_0 = this;
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    tmp_0.db_1 = ['vertexPosition', 'vertexNormal', 'vertexTexCoord'];
    var tmp_1 = this;
    // Inline function 'kotlin.emptyArray' call
    tmp_1.eb_1 = [];
  }
  var Companion_instance;
  function Companion_getInstance() {
    if (Companion_instance == null)
      new Companion();
    return Companion_instance;
  }
  function Program(gl, vertexShader, fragmentShader, attributeBindings) {
    Companion_getInstance();
    attributeBindings = attributeBindings === VOID ? Companion_getInstance().db_1 : attributeBindings;
    UniformProvider.call(this, ['program']);
    this.ib_1 = gl;
    this.jb_1 = vertexShader;
    this.kb_1 = fragmentShader;
    var tmp = this;
    var tmp0_elvis_lhs = this.ib_1.createProgram();
    var tmp_0;
    if (tmp0_elvis_lhs == null) {
      throw Error_init_$Create$('Could not create WebGL program.');
    } else {
      tmp_0 = tmp0_elvis_lhs;
    }
    tmp.lb_1 = tmp_0;
    this.ib_1.attachShader(this.lb_1, this.jb_1.pb_1);
    this.ib_1.attachShader(this.lb_1, this.kb_1.pb_1);
    var attributeIndex = 0;
    // Inline function 'kotlin.collections.forEach' call
    var inductionVariable = 0;
    var last = attributeBindings.length;
    while (inductionVariable < last) {
      var element = attributeBindings[inductionVariable];
      inductionVariable = inductionVariable + 1 | 0;
      var _unary__edvuaz = attributeIndex;
      attributeIndex = _unary__edvuaz + 1 | 0;
      this.ib_1.bindAttribLocation(this.lb_1, _unary__edvuaz, element);
    }
    this.ib_1.linkProgram(this.lb_1);
    if (equals(this.ib_1.getProgramParameter(this.lb_1, WebGLRenderingContext.LINK_STATUS), false)) {
      throw Error_init_$Create$('Could not link shaders [vertex shader: ' + this.jb_1.ob_1 + ']:[fragment shader: ' + this.kb_1.ob_1 + '\n' + this.ib_1.getProgramInfoLog(this.lb_1));
    }
    this.ea([new ProgramReflection(this.ib_1, this.lb_1)]);
    var tmp_1 = Companion_getInstance();
    // Inline function 'kotlin.collections.plus' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp$ret$2 = Companion_getInstance().eb_1;
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp$ret$5 = [this];
    tmp_1.eb_1 = tmp$ret$2.concat(tmp$ret$5);
  }
  function QuadGeometry(gl) {
    Geometry.call(this);
    this.qb_1 = gl;
    this.rb_1 = this.qb_1.createBuffer();
    this.qb_1.bindBuffer(WebGLRenderingContext.ARRAY_BUFFER, this.rb_1);
    var tmp = WebGLRenderingContext.ARRAY_BUFFER;
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp$ret$2 = [-0.5, -0.5, 0.5, -0.5, 0.5, 0.5, 0.5, -0.5, 0.5, 0.5, 0.5, 0.5];
    this.qb_1.bufferData(tmp, new Float32Array(tmp$ret$2), WebGLRenderingContext.STATIC_DRAW);
    this.sb_1 = this.qb_1.createBuffer();
    this.qb_1.bindBuffer(WebGLRenderingContext.ARRAY_BUFFER, this.sb_1);
    var tmp_0 = WebGLRenderingContext.ARRAY_BUFFER;
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp$ret$5 = [0.5, 0.5, 0.5, 0.5, 0.0, 0.5, 0.5, 0.5, 0.0, 0.0, 0.5, 0.5];
    this.qb_1.bufferData(tmp_0, new Float32Array(tmp$ret$5), WebGLRenderingContext.STATIC_DRAW);
    this.tb_1 = this.qb_1.createBuffer();
    this.qb_1.bindBuffer(WebGLRenderingContext.ELEMENT_ARRAY_BUFFER, this.tb_1);
    var tmp_1 = WebGLRenderingContext.ELEMENT_ARRAY_BUFFER;
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp$ret$8 = [0, 1, 2, 1, 2, 3];
    this.qb_1.bufferData(tmp_1, new Uint16Array(tmp$ret$8), WebGLRenderingContext.STATIC_DRAW);
    this.ub_1 = this.qb_1.createVertexArray();
    this.qb_1.bindVertexArray(this.ub_1);
    this.qb_1.bindBuffer(WebGLRenderingContext.ARRAY_BUFFER, this.rb_1);
    this.qb_1.enableVertexAttribArray(0);
    this.qb_1.vertexAttribPointer(0, 3, WebGLRenderingContext.FLOAT, false, 0, 0);
    this.qb_1.bindBuffer(WebGLRenderingContext.ARRAY_BUFFER, this.sb_1);
    this.qb_1.enableVertexAttribArray(1);
    this.qb_1.vertexAttribPointer(1, 3, WebGLRenderingContext.FLOAT, false, 0, 0);
    this.qb_1.bindVertexArray(null);
  }
  protoOf(QuadGeometry).vb = function () {
    this.qb_1.bindVertexArray(this.ub_1);
    this.qb_1.bindBuffer(WebGLRenderingContext.ELEMENT_ARRAY_BUFFER, this.tb_1);
    this.qb_1.drawElements(WebGLRenderingContext.TRIANGLES, 6, WebGLRenderingContext.UNSIGNED_SHORT, 0);
  };
  function Scene(gl) {
    this.e9_1 = gl;
    this.f9_1 = new OrthoCamera();
    this.g9_1 = Vec2_init_$Create$(0.0, 0.0);
    this.h9_1 = 0.0;
    this.i9_1 = Vec2_init_$Create$(1.0, 1.0);
    this.j9_1 = 0.0;
    this.k9_1 = 0.0;
    this.l9_1 = Mat4_init_$Create$(new Float32Array([]));
    this.m9_1 = Mat4_init_$Create$(new Float32Array([]));
    this.n9_1 = new Shader(this.e9_1, WebGLRenderingContext.VERTEX_SHADER, 'idle-vs.glsl');
    this.o9_1 = new Shader(this.e9_1, WebGLRenderingContext.FRAGMENT_SHADER, 'solid-fs.glsl');
    this.p9_1 = new Shader(this.e9_1, WebGLRenderingContext.FRAGMENT_SHADER, 'striped-fs.glsl');
    this.q9_1 = new Program(this.e9_1, this.n9_1, this.o9_1, Companion_getInstance().cb_1);
    this.r9_1 = new Program(this.e9_1, this.n9_1, this.p9_1, Companion_getInstance().cb_1);
    this.s9_1 = new QuadGeometry(this.e9_1);
    this.t9_1 = new TriangleGeometry(this.e9_1);
    var tmp = this;
    // Inline function 'kotlin.apply' call
    var this_0 = new Material(this.r9_1);
    var tmp0_safe_receiver = this_0.y6('stripeWidth');
    if (tmp0_safe_receiver == null)
      null;
    else
      tmp0_safe_receiver.ua(new Float32Array([0.6]));
    tmp.u9_1 = this_0;
    var tmp_0 = this;
    // Inline function 'kotlin.apply' call
    var this_1 = new Material(this.r9_1);
    var tmp0_safe_receiver_0 = this_1.y6('stripeWidth');
    if (tmp0_safe_receiver_0 == null)
      null;
    else
      tmp0_safe_receiver_0.ua(new Float32Array([0.05]));
    tmp_0.v9_1 = this_1;
    this.w9_1 = new Mesh(this.u9_1, this.t9_1);
    this.x9_1 = new Mesh(this.v9_1, this.t9_1);
  }
  protoOf(Scene).y9 = function (canvas) {
    this.e9_1.viewport(0, 0, canvas.width, canvas.height);
    this.f9_1.ab(canvas.width / canvas.height);
  };
  protoOf(Scene).aa = function (keysPressed) {
    var timeAtFirstFrame = (new Date()).getTime();
    var timeAtLastFrame = timeAtFirstFrame;
    var timeAtThisFrame = (new Date()).getTime();
    var dt = (timeAtThisFrame - timeAtLastFrame) / 1000.0;
    var t = (timeAtThisFrame - timeAtFirstFrame) / 1000.0;
    timeAtLastFrame = timeAtThisFrame;
    if (keysPressed.s('W')) {
      var _receiver__tnumb7 = this.f9_1.la_1;
      // Inline function 'vision.gears.webglmath.Vec2.y' call
      // Inline function 'org.khronos.webgl.get' call
      // Inline function 'kotlin.js.asDynamic' call
      // Inline function 'vision.gears.webglmath.Vec2.y' call
      var value = _receiver__tnumb7.bb_1[1] + 0.01;
      // Inline function 'org.khronos.webgl.set' call
      // Inline function 'kotlin.js.asDynamic' call
      _receiver__tnumb7.bb_1[1] = value;
    }
    if (keysPressed.s('A')) {
      var _receiver__tnumb7_0 = this.f9_1.la_1;
      // Inline function 'vision.gears.webglmath.Vec2.x' call
      // Inline function 'org.khronos.webgl.get' call
      // Inline function 'kotlin.js.asDynamic' call
      // Inline function 'vision.gears.webglmath.Vec2.x' call
      var value_0 = _receiver__tnumb7_0.bb_1[0] - 0.01;
      // Inline function 'org.khronos.webgl.set' call
      // Inline function 'kotlin.js.asDynamic' call
      _receiver__tnumb7_0.bb_1[0] = value_0;
    }
    if (keysPressed.s('S')) {
      var _receiver__tnumb7_1 = this.f9_1.la_1;
      // Inline function 'vision.gears.webglmath.Vec2.y' call
      // Inline function 'org.khronos.webgl.get' call
      // Inline function 'kotlin.js.asDynamic' call
      // Inline function 'vision.gears.webglmath.Vec2.y' call
      var value_1 = _receiver__tnumb7_1.bb_1[1] - 0.01;
      // Inline function 'org.khronos.webgl.set' call
      // Inline function 'kotlin.js.asDynamic' call
      _receiver__tnumb7_1.bb_1[1] = value_1;
    }
    if (keysPressed.s('D')) {
      var _receiver__tnumb7_2 = this.f9_1.la_1;
      // Inline function 'vision.gears.webglmath.Vec2.x' call
      // Inline function 'org.khronos.webgl.get' call
      // Inline function 'kotlin.js.asDynamic' call
      // Inline function 'vision.gears.webglmath.Vec2.x' call
      var value_2 = _receiver__tnumb7_2.bb_1[0] + 0.01;
      // Inline function 'org.khronos.webgl.set' call
      // Inline function 'kotlin.js.asDynamic' call
      _receiver__tnumb7_2.bb_1[0] = value_2;
    }
    if (keysPressed.s('Q')) {
      var _receiver__tnumb7_3 = this.f9_1;
      _receiver__tnumb7_3.ma_1 = _receiver__tnumb7_3.ma_1 + 0.01;
    }
    if (keysPressed.s('E')) {
      var _receiver__tnumb7_4 = this.f9_1;
      _receiver__tnumb7_4.ma_1 = _receiver__tnumb7_4.ma_1 - 0.01;
    }
    if (keysPressed.s('RIGHT')) {
      var _receiver__tnumb7_5 = this.g9_1;
      // Inline function 'vision.gears.webglmath.Vec2.x' call
      // Inline function 'org.khronos.webgl.get' call
      // Inline function 'kotlin.js.asDynamic' call
      // Inline function 'vision.gears.webglmath.Vec2.x' call
      var value_3 = _receiver__tnumb7_5.bb_1[0] + 0.01;
      // Inline function 'org.khronos.webgl.set' call
      // Inline function 'kotlin.js.asDynamic' call
      _receiver__tnumb7_5.bb_1[0] = value_3;
    }
    if (keysPressed.s('LEFT')) {
      var _receiver__tnumb7_6 = this.g9_1;
      // Inline function 'vision.gears.webglmath.Vec2.x' call
      // Inline function 'org.khronos.webgl.get' call
      // Inline function 'kotlin.js.asDynamic' call
      // Inline function 'vision.gears.webglmath.Vec2.x' call
      var value_4 = _receiver__tnumb7_6.bb_1[0] - 0.01;
      // Inline function 'org.khronos.webgl.set' call
      // Inline function 'kotlin.js.asDynamic' call
      _receiver__tnumb7_6.bb_1[0] = value_4;
    }
    if (keysPressed.s('UP')) {
      var _receiver__tnumb7_7 = this.g9_1;
      // Inline function 'vision.gears.webglmath.Vec2.y' call
      // Inline function 'org.khronos.webgl.get' call
      // Inline function 'kotlin.js.asDynamic' call
      // Inline function 'vision.gears.webglmath.Vec2.y' call
      var value_5 = _receiver__tnumb7_7.bb_1[1] + 0.01;
      // Inline function 'org.khronos.webgl.set' call
      // Inline function 'kotlin.js.asDynamic' call
      _receiver__tnumb7_7.bb_1[1] = value_5;
    }
    if (keysPressed.s('DOWN')) {
      var _receiver__tnumb7_8 = this.g9_1;
      // Inline function 'vision.gears.webglmath.Vec2.y' call
      // Inline function 'org.khronos.webgl.get' call
      // Inline function 'kotlin.js.asDynamic' call
      // Inline function 'vision.gears.webglmath.Vec2.y' call
      var value_6 = _receiver__tnumb7_8.bb_1[1] - 0.01;
      // Inline function 'org.khronos.webgl.set' call
      // Inline function 'kotlin.js.asDynamic' call
      _receiver__tnumb7_8.bb_1[1] = value_6;
    }
    if (keysPressed.s('F')) {
      this.h9_1 = this.h9_1 + 0.001;
    }
    if (keysPressed.s('R')) {
      // Inline function 'vision.gears.webglmath.Vec2.timesAssign' call
      var this_0 = this.i9_1;
      var _array__4zh2yp = this_0.bb_1;
      // Inline function 'org.khronos.webgl.get' call
      // Inline function 'kotlin.js.asDynamic' call
      // Inline function 'org.khronos.webgl.set' call
      // Inline function 'kotlin.js.asDynamic' call
      _array__4zh2yp[0] = _array__4zh2yp[0] * 1.001;
      var _array__4zh2yp_0 = this_0.bb_1;
      // Inline function 'org.khronos.webgl.get' call
      // Inline function 'kotlin.js.asDynamic' call
      // Inline function 'org.khronos.webgl.set' call
      // Inline function 'kotlin.js.asDynamic' call
      _array__4zh2yp_0[1] = _array__4zh2yp_0[1] * 1.001;
    }
    this.e9_1.clearColor(1.0, 0.0, 0.0, 1.0);
    this.e9_1.clearDepth(1.0);
    this.e9_1.clear(WebGLRenderingContext.COLOR_BUFFER_BIT | WebGLRenderingContext.DEPTH_BUFFER_BIT);
    this.f9_1.sa();
    var tmp = this.l9_1.ua(new Float32Array([]));
    // Inline function 'vision.gears.webglmath.Vec2.x' call
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_0 = this.i9_1.bb_1[0];
    // Inline function 'vision.gears.webglmath.Vec2.y' call
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp$ret$62 = this.i9_1.bb_1[1];
    var tmp_1 = tmp.va(tmp_0, tmp$ret$62).wb(this.h9_1, 0.0, 0.0, 1.0);
    // Inline function 'vision.gears.webglmath.Vec2.x' call
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_2 = this.g9_1.bb_1[0];
    // Inline function 'vision.gears.webglmath.Vec2.y' call
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp$ret$68 = this.g9_1.bb_1[1];
    tmp_1.xb(tmp_2, tmp$ret$68);
    this.m9_1.ua(new Float32Array([]));
    this.w9_1.ha([]);
    this.l9_1.yb(this.e9_1, ensureNotNull(this.e9_1.getUniformLocation(this.r9_1.lb_1, 'gameObject.modelMatrix')));
    this.f9_1.pa().yb(this.e9_1, ensureNotNull(this.e9_1.getUniformLocation(this.r9_1.lb_1, 'camera.viewProjMatrix')));
    this.x9_1.ha([]);
    this.m9_1.yb(this.e9_1, ensureNotNull(this.e9_1.getUniformLocation(this.r9_1.lb_1, 'gameObject.modelMatrix')));
    this.f9_1.pa().yb(this.e9_1, ensureNotNull(this.e9_1.getUniformLocation(this.r9_1.lb_1, 'camera.viewProjMatrix')));
  };
  function Shader$lambda$lambda(match) {
    // Inline function 'kotlin.text.plus' call
    var this_0 = _Char___init__impl__6a9atx(27);
    return toString(this_0) + '[46m' + match.v() + toString(_Char___init__impl__6a9atx(27)) + '[31m';
  }
  function Shader$lambda($request, $errorMessage, this$0) {
    return function (it) {
      var source = $request.responseText;
      var tmp;
      if (source === '') {
        $errorMessage._v = this$0.ob_1 + ' is empty.';
        tmp = Unit_instance;
      } else {
        var tmp_0 = Regex_init_$Create$('[^\x00-\x7F]');
        var marked = tmp_0.p6(source, Shader$lambda$lambda);
        var tmp_1;
        if (!(marked === source)) {
          $errorMessage._v = 'Shader source file ' + this$0.ob_1 + ' has \x1B[46mnon-ASCII\x1B[31m characters.\n' + marked;
          tmp_1 = Unit_instance;
        } else {
          this$0.mb_1.shaderSource(this$0.pb_1, source);
          this$0.mb_1.compileShader(this$0.pb_1);
          var tmp_2;
          if (equals(this$0.mb_1.getShaderParameter(this$0.pb_1, WebGLRenderingContext.COMPILE_STATUS), false)) {
            var errorPrefix = Regex_init_$Create$('ERROR: 0');
            var tmp0_elvis_lhs = this$0.mb_1.getShaderInfoLog(this$0.pb_1);
            $errorMessage._v = 'Shader ' + this$0.ob_1 + ' had compilation errors.\n' + errorPrefix.o6(tmp0_elvis_lhs == null ? 'FAILED TO OBTAIN LOG.' : tmp0_elvis_lhs, $request.responseURL);
            tmp_2 = Unit_instance;
          }
          tmp_1 = tmp_2;
        }
        tmp = tmp_1;
      }
      return Unit_instance;
    };
  }
  function Shader(gl, shaderType, sourceUrl) {
    this.mb_1 = gl;
    this.nb_1 = shaderType;
    this.ob_1 = sourceUrl;
    this.pb_1 = this.mb_1.createShader(this.nb_1);
    var request = new XMLHttpRequest();
    request.overrideMimeType('text/plain');
    request.open('GET', this.ob_1, false);
    var errorMessage = {_v: null};
    request.onloadend = Shader$lambda(request, errorMessage, this);
    request.send();
    if (!(errorMessage._v == null)) {
      throw Error_init_$Create$(errorMessage._v);
    }
  }
  function TriangleGeometry(gl) {
    Geometry.call(this);
    this.zb_1 = gl;
    this.ac_1 = this.zb_1.createBuffer();
    this.zb_1.bindBuffer(WebGLRenderingContext.ARRAY_BUFFER, this.ac_1);
    var tmp = WebGLRenderingContext.ARRAY_BUFFER;
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp$ret$2 = [-0.5, -0.5, 0.5, -0.5, 0.5, 0.5, 0.5, 0.0, 0.5];
    this.zb_1.bufferData(tmp, new Float32Array(tmp$ret$2), WebGLRenderingContext.STATIC_DRAW);
    this.bc_1 = this.zb_1.createBuffer();
    this.zb_1.bindBuffer(WebGLRenderingContext.ARRAY_BUFFER, this.bc_1);
    var tmp_0 = WebGLRenderingContext.ARRAY_BUFFER;
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp$ret$5 = [1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0];
    this.zb_1.bufferData(tmp_0, new Float32Array(tmp$ret$5), WebGLRenderingContext.STATIC_DRAW);
    this.cc_1 = this.zb_1.createBuffer();
    this.zb_1.bindBuffer(WebGLRenderingContext.ELEMENT_ARRAY_BUFFER, this.cc_1);
    var tmp_1 = WebGLRenderingContext.ELEMENT_ARRAY_BUFFER;
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp$ret$8 = [0, 1, 2];
    this.zb_1.bufferData(tmp_1, new Uint16Array(tmp$ret$8), WebGLRenderingContext.STATIC_DRAW);
    this.dc_1 = this.zb_1.createVertexArray();
    this.zb_1.bindVertexArray(this.dc_1);
    this.zb_1.bindBuffer(WebGLRenderingContext.ARRAY_BUFFER, this.ac_1);
    this.zb_1.enableVertexAttribArray(0);
    this.zb_1.vertexAttribPointer(0, 3, WebGLRenderingContext.FLOAT, false, 0, 0);
    this.zb_1.bindBuffer(WebGLRenderingContext.ARRAY_BUFFER, this.bc_1);
    this.zb_1.enableVertexAttribArray(1);
    this.zb_1.vertexAttribPointer(1, 3, WebGLRenderingContext.FLOAT, false, 0, 0);
    this.zb_1.bindVertexArray(null);
  }
  protoOf(TriangleGeometry).vb = function () {
    this.zb_1.bindVertexArray(this.dc_1);
    this.zb_1.bindBuffer(WebGLRenderingContext.ELEMENT_ARRAY_BUFFER, this.cc_1);
    this.zb_1.drawElements(WebGLRenderingContext.TRIANGLES, 3, WebGLRenderingContext.UNSIGNED_SHORT, 0);
  };
  function Drawable() {
  }
  protoOf(Drawable).ga = function (target) {
  };
  function Geometry() {
    Drawable.call(this);
  }
  protoOf(Geometry).ha = function (uniformProviders) {
    this.vb();
  };
  protoOf(Geometry).ga = function (target) {
  };
  function IVec1_init_$Init$(u, $this) {
    u = u === VOID ? 0 : u;
    IVec1.call($this, null, 0);
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    $this.ec_1[0] = u;
    return $this;
  }
  function IVec1_init_$Create$(u) {
    return IVec1_init_$Init$(u, objectCreate(protoOf(IVec1)));
  }
  function Companion_0() {
    Companion_instance_0 = this;
    this.fc_1 = IVec1_init_$Create$();
    this.gc_1 = IVec1_init_$Create$(1);
  }
  var Companion_instance_0;
  function Companion_getInstance_0() {
    if (Companion_instance_0 == null)
      new Companion_0();
    return Companion_instance_0;
  }
  function IVec1(backingStorage, offset) {
    Companion_getInstance_0();
    offset = offset === VOID ? 0 : offset;
    var tmp = this;
    var tmp1_elvis_lhs = backingStorage == null ? null : backingStorage.subarray(offset, offset + 1 | 0);
    tmp.ec_1 = tmp1_elvis_lhs == null ? new Int32Array(1) : tmp1_elvis_lhs;
  }
  protoOf(IVec1).hc = function () {
    return this.ec_1;
  };
  protoOf(IVec1).ic = function (gl, uniformLocation, samplerIndex) {
    gl.uniform1iv(uniformLocation, this.ec_1);
  };
  function IVec1Array_init_$Init$(size, $this) {
    IVec1Array.call($this, null, size, size);
    return $this;
  }
  function IVec1Array_init_$Create$(size) {
    return IVec1Array_init_$Init$(size, objectCreate(protoOf(IVec1Array)));
  }
  function IVec1Array(backingStorage, startIndex, endIndex) {
    startIndex = startIndex === VOID ? 0 : startIndex;
    endIndex = endIndex === VOID ? 0 : endIndex;
    IVecArray.call(this);
    var tmp = this;
    var tmp1_elvis_lhs = backingStorage == null ? null : backingStorage.subarray(startIndex, endIndex);
    tmp.kc_1 = tmp1_elvis_lhs == null ? new Int32Array(startIndex) : tmp1_elvis_lhs;
  }
  protoOf(IVec1Array).hc = function () {
    return this.kc_1;
  };
  protoOf(IVec1Array).ic = function (gl, uniformLocation, samplerIndex) {
    gl.uniform1iv(uniformLocation, this.kc_1);
  };
  function IVec2_init_$Init$(u, v, $this) {
    u = u === VOID ? 0 : u;
    v = v === VOID ? 0 : v;
    IVec2.call($this, null, 0);
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    $this.lc_1[0] = u;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    $this.lc_1[1] = v;
    return $this;
  }
  function IVec2_init_$Create$(u, v) {
    return IVec2_init_$Init$(u, v, objectCreate(protoOf(IVec2)));
  }
  function Companion_1() {
    Companion_instance_1 = this;
    this.mc_1 = IVec2_init_$Create$();
    this.nc_1 = IVec2_init_$Create$(1, 1);
  }
  var Companion_instance_1;
  function Companion_getInstance_1() {
    if (Companion_instance_1 == null)
      new Companion_1();
    return Companion_instance_1;
  }
  function IVec2(backingStorage, offset) {
    Companion_getInstance_1();
    offset = offset === VOID ? 0 : offset;
    var tmp = this;
    var tmp1_elvis_lhs = backingStorage == null ? null : backingStorage.subarray(offset, offset + 2 | 0);
    tmp.lc_1 = tmp1_elvis_lhs == null ? new Int32Array(2) : tmp1_elvis_lhs;
  }
  protoOf(IVec2).hc = function () {
    return this.lc_1;
  };
  protoOf(IVec2).ic = function (gl, uniformLocation, samplerIndex) {
    gl.uniform2iv(uniformLocation, this.lc_1);
  };
  function IVec3_init_$Init$(u, v, s, $this) {
    u = u === VOID ? 0 : u;
    v = v === VOID ? 0 : v;
    s = s === VOID ? 0 : s;
    IVec3.call($this, null, 0);
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    $this.oc_1[0] = u;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    $this.oc_1[1] = v;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    $this.oc_1[2] = s;
    return $this;
  }
  function IVec3_init_$Create$(u, v, s) {
    return IVec3_init_$Init$(u, v, s, objectCreate(protoOf(IVec3)));
  }
  function Companion_2() {
    Companion_instance_2 = this;
    this.pc_1 = IVec3_init_$Create$();
    this.qc_1 = IVec3_init_$Create$(1, 1, 1);
  }
  var Companion_instance_2;
  function Companion_getInstance_2() {
    if (Companion_instance_2 == null)
      new Companion_2();
    return Companion_instance_2;
  }
  function IVec3(backingStorage, offset) {
    Companion_getInstance_2();
    offset = offset === VOID ? 0 : offset;
    var tmp = this;
    var tmp1_elvis_lhs = backingStorage == null ? null : backingStorage.subarray(offset, offset + 3 | 0);
    tmp.oc_1 = tmp1_elvis_lhs == null ? new Int32Array(3) : tmp1_elvis_lhs;
  }
  protoOf(IVec3).hc = function () {
    return this.oc_1;
  };
  protoOf(IVec3).ic = function (gl, uniformLocation, samplerIndex) {
    gl.uniform3iv(uniformLocation, this.oc_1);
  };
  function IVec4_init_$Init$(u, v, s, t, $this) {
    u = u === VOID ? 0 : u;
    v = v === VOID ? 0 : v;
    s = s === VOID ? 0 : s;
    t = t === VOID ? 1 : t;
    IVec4.call($this, null, 0);
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    $this.rc_1[0] = u;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    $this.rc_1[1] = v;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    $this.rc_1[2] = s;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    $this.rc_1[3] = t;
    return $this;
  }
  function IVec4_init_$Create$(u, v, s, t) {
    return IVec4_init_$Init$(u, v, s, t, objectCreate(protoOf(IVec4)));
  }
  function Companion_3() {
    Companion_instance_3 = this;
    this.sc_1 = IVec4_init_$Create$(0, 0, 0, 0);
    this.tc_1 = IVec4_init_$Create$(1, 1, 1, 1);
  }
  var Companion_instance_3;
  function Companion_getInstance_3() {
    if (Companion_instance_3 == null)
      new Companion_3();
    return Companion_instance_3;
  }
  function IVec4(backingStorage, offset) {
    Companion_getInstance_3();
    offset = offset === VOID ? 0 : offset;
    var tmp = this;
    var tmp1_elvis_lhs = backingStorage == null ? null : backingStorage.subarray(offset, offset + 4 | 0);
    tmp.rc_1 = tmp1_elvis_lhs == null ? new Int32Array(4) : tmp1_elvis_lhs;
  }
  protoOf(IVec4).hc = function () {
    return this.rc_1;
  };
  protoOf(IVec4).ic = function (gl, uniformLocation, samplerIndex) {
    gl.uniform4iv(uniformLocation, this.rc_1);
  };
  function IVecArray() {
  }
  function Mat4_init_$Init$(elements, $this) {
    Mat4.call($this, null, 0);
    var tmp = 0;
    // Inline function 'kotlin.arrayOfNulls' call
    var tmp_0 = Array(16);
    while (tmp < 16) {
      var tmp_1 = tmp;
      var tmp0_elvis_lhs = getOrNull(elements, imul(tmp_1 % 4 | 0, 4) + (tmp_1 / 4 | 0) | 0);
      tmp_0[tmp_1] = tmp0_elvis_lhs == null ? (tmp_1 % 5 | 0) === 0 ? 1.0 : 0.0 : tmp0_elvis_lhs;
      tmp = tmp + 1 | 0;
    }
    var allElements = tmp_0;
    $this.qa_1.set(allElements);
    return $this;
  }
  function Mat4_init_$Create$(elements) {
    return Mat4_init_$Init$(elements, objectCreate(protoOf(Mat4)));
  }
  function Mat4(backingStorage, offset) {
    offset = offset === VOID ? 0 : offset;
    var tmp = this;
    var tmp1_elvis_lhs = backingStorage == null ? null : backingStorage.subarray(offset, offset + 16 | 0);
    tmp.qa_1 = tmp1_elvis_lhs == null ? new Float32Array(16) : tmp1_elvis_lhs;
  }
  protoOf(Mat4).hc = function () {
    return this.qa_1;
  };
  protoOf(Mat4).ua = function (values) {
    var tmp = 0;
    // Inline function 'kotlin.arrayOfNulls' call
    var tmp_0 = Array(16);
    while (tmp < 16) {
      var tmp_1 = tmp;
      var tmp0_elvis_lhs = getOrNull(values, imul(tmp_1 % 4 | 0, 4) + (tmp_1 / 4 | 0) | 0);
      tmp_0[tmp_1] = tmp0_elvis_lhs == null ? (tmp_1 % 5 | 0) === 0 ? 1.0 : 0.0 : tmp0_elvis_lhs;
      tmp = tmp + 1 | 0;
    }
    var allElements = tmp_0;
    this.qa_1.set(allElements);
    return this;
  };
  protoOf(Mat4).wa = function (s) {
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp = s.bb_1[0];
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp$ret$3 = s.bb_1[1];
    return this.uc(tmp, tmp$ret$3, 1.0);
  };
  protoOf(Mat4).uc = function (sx, sy, sz) {
    var _array__4zh2yp = this.qa_1;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    _array__4zh2yp[0] = _array__4zh2yp[0] * sx;
    var _array__4zh2yp_0 = this.qa_1;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    _array__4zh2yp_0[1] = _array__4zh2yp_0[1] * sx;
    var _array__4zh2yp_1 = this.qa_1;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    _array__4zh2yp_1[2] = _array__4zh2yp_1[2] * sx;
    var _array__4zh2yp_2 = this.qa_1;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    _array__4zh2yp_2[3] = _array__4zh2yp_2[3] * sx;
    var _array__4zh2yp_3 = this.qa_1;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    _array__4zh2yp_3[4] = _array__4zh2yp_3[4] * sy;
    var _array__4zh2yp_4 = this.qa_1;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    _array__4zh2yp_4[5] = _array__4zh2yp_4[5] * sy;
    var _array__4zh2yp_5 = this.qa_1;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    _array__4zh2yp_5[6] = _array__4zh2yp_5[6] * sy;
    var _array__4zh2yp_6 = this.qa_1;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    _array__4zh2yp_6[7] = _array__4zh2yp_6[7] * sy;
    var _array__4zh2yp_7 = this.qa_1;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    _array__4zh2yp_7[8] = _array__4zh2yp_7[8] * sz;
    var _array__4zh2yp_8 = this.qa_1;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    _array__4zh2yp_8[9] = _array__4zh2yp_8[9] * sz;
    var _array__4zh2yp_9 = this.qa_1;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    _array__4zh2yp_9[10] = _array__4zh2yp_9[10] * sz;
    var _array__4zh2yp_10 = this.qa_1;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    _array__4zh2yp_10[11] = _array__4zh2yp_10[11] * sz;
    return this;
  };
  protoOf(Mat4).va = function (sx, sy, sz, $super) {
    sx = sx === VOID ? 1.0 : sx;
    sy = sy === VOID ? 1.0 : sy;
    sz = sz === VOID ? 1.0 : sz;
    return $super === VOID ? this.uc(sx, sy, sz) : $super.uc.call(this, sx, sy, sz);
  };
  protoOf(Mat4).wb = function (angle, axisX, axisY, axisZ) {
    var x = axisX;
    var y = axisY;
    var z = axisZ;
    var axisLength2 = x * x + y * y + z * z;
    if (axisLength2 < 1.0E-4) {
      x = 0.0;
      y = 0.0;
      z = 1.0;
    } else if (axisLength2 < 0.999 || axisLength2 > 1.001) {
      // Inline function 'kotlin.math.sqrt' call
      var axisLength = Math.sqrt(axisLength2);
      x = x / axisLength;
      y = y / axisLength;
      z = z / axisLength;
    }
    // Inline function 'kotlin.math.cos' call
    var cosa = Math.cos(angle);
    // Inline function 'kotlin.math.sin' call
    var sina = Math.sin(angle);
    var C = 1.0 - cosa;
    var m11 = x * x * C + cosa;
    var m21 = x * y * C - z * sina;
    var m31 = x * z * C + y * sina;
    var m12 = y * x * C + z * sina;
    var m22 = y * y * C + cosa;
    var m32 = y * z * C - x * sina;
    var m13 = z * x * C - y * sina;
    var m23 = z * y * C + x * sina;
    var m33 = z * z * C + cosa;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp = this.qa_1[0] * m11;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_0 = tmp + this.qa_1[4] * m21;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var t0 = tmp_0 + this.qa_1[8] * m31;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_1 = this.qa_1[0] * m12;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_2 = tmp_1 + this.qa_1[4] * m22;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var t4 = tmp_2 + this.qa_1[8] * m32;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_3 = this.qa_1[0] * m13;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_4 = tmp_3 + this.qa_1[4] * m23;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var t8 = tmp_4 + this.qa_1[8] * m33;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_5 = this.qa_1[1] * m11;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_6 = tmp_5 + this.qa_1[5] * m21;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var t1 = tmp_6 + this.qa_1[9] * m31;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_7 = this.qa_1[1] * m12;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_8 = tmp_7 + this.qa_1[5] * m22;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var t5 = tmp_8 + this.qa_1[9] * m32;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_9 = this.qa_1[1] * m13;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_10 = tmp_9 + this.qa_1[5] * m23;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var t9 = tmp_10 + this.qa_1[9] * m33;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_11 = this.qa_1[2] * m11;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_12 = tmp_11 + this.qa_1[6] * m21;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var t2 = tmp_12 + this.qa_1[10] * m31;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_13 = this.qa_1[2] * m12;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_14 = tmp_13 + this.qa_1[6] * m22;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var t6 = tmp_14 + this.qa_1[10] * m32;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_15 = this.qa_1[2] * m13;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_16 = tmp_15 + this.qa_1[6] * m23;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var t10 = tmp_16 + this.qa_1[10] * m33;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_17 = this.qa_1[3] * m11;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_18 = tmp_17 + this.qa_1[7] * m21;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var t3 = tmp_18 + this.qa_1[11] * m31;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_19 = this.qa_1[3] * m12;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_20 = tmp_19 + this.qa_1[7] * m22;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var t7 = tmp_20 + this.qa_1[11] * m32;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_21 = this.qa_1[3] * m13;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_22 = tmp_21 + this.qa_1[7] * m23;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var t11 = tmp_22 + this.qa_1[11] * m33;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.qa_1[0] = t0;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.qa_1[4] = t4;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.qa_1[8] = t8;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.qa_1[1] = t1;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.qa_1[5] = t5;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.qa_1[9] = t9;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.qa_1[2] = t2;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.qa_1[6] = t6;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.qa_1[10] = t10;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.qa_1[3] = t3;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.qa_1[7] = t7;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.qa_1[11] = t11;
    return this;
  };
  protoOf(Mat4).xa = function (angle, axisX, axisY, axisZ, $super) {
    angle = angle === VOID ? 0.0 : angle;
    axisX = axisX === VOID ? 0.0 : axisX;
    axisY = axisY === VOID ? 0.0 : axisY;
    axisZ = axisZ === VOID ? 0.0 : axisZ;
    return $super === VOID ? this.wb(angle, axisX, axisY, axisZ) : $super.wb.call(this, angle, axisX, axisY, axisZ);
  };
  protoOf(Mat4).ya = function (t) {
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp = t.bb_1[0];
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp$ret$3 = t.bb_1[1];
    return this.xb(tmp, tmp$ret$3);
  };
  protoOf(Mat4).vc = function (x, y, z) {
    var _array__4zh2yp = this.qa_1;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp = _array__4zh2yp[0];
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    _array__4zh2yp[0] = tmp + this.qa_1[12] * x;
    var _array__4zh2yp_0 = this.qa_1;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_0 = _array__4zh2yp_0[4];
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    _array__4zh2yp_0[4] = tmp_0 + this.qa_1[12] * y;
    var _array__4zh2yp_1 = this.qa_1;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_1 = _array__4zh2yp_1[8];
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    _array__4zh2yp_1[8] = tmp_1 + this.qa_1[12] * z;
    var _array__4zh2yp_2 = this.qa_1;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_2 = _array__4zh2yp_2[1];
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    _array__4zh2yp_2[1] = tmp_2 + this.qa_1[13] * x;
    var _array__4zh2yp_3 = this.qa_1;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_3 = _array__4zh2yp_3[5];
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    _array__4zh2yp_3[5] = tmp_3 + this.qa_1[13] * y;
    var _array__4zh2yp_4 = this.qa_1;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_4 = _array__4zh2yp_4[9];
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    _array__4zh2yp_4[9] = tmp_4 + this.qa_1[13] * z;
    var _array__4zh2yp_5 = this.qa_1;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_5 = _array__4zh2yp_5[2];
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    _array__4zh2yp_5[2] = tmp_5 + this.qa_1[14] * x;
    var _array__4zh2yp_6 = this.qa_1;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_6 = _array__4zh2yp_6[6];
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    _array__4zh2yp_6[6] = tmp_6 + this.qa_1[14] * y;
    var _array__4zh2yp_7 = this.qa_1;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_7 = _array__4zh2yp_7[10];
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    _array__4zh2yp_7[10] = tmp_7 + this.qa_1[14] * z;
    var _array__4zh2yp_8 = this.qa_1;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_8 = _array__4zh2yp_8[3];
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    _array__4zh2yp_8[3] = tmp_8 + this.qa_1[15] * x;
    var _array__4zh2yp_9 = this.qa_1;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_9 = _array__4zh2yp_9[7];
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    _array__4zh2yp_9[7] = tmp_9 + this.qa_1[15] * y;
    var _array__4zh2yp_10 = this.qa_1;
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_10 = _array__4zh2yp_10[11];
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    _array__4zh2yp_10[11] = tmp_10 + this.qa_1[15] * z;
    return this;
  };
  protoOf(Mat4).xb = function (x, y, z, $super) {
    x = x === VOID ? 0.0 : x;
    y = y === VOID ? 0.0 : y;
    z = z === VOID ? 0.0 : z;
    return $super === VOID ? this.vc(x, y, z) : $super.vc.call(this, x, y, z);
  };
  protoOf(Mat4).za = function () {
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var a00 = this.qa_1[0];
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var a01 = this.qa_1[1];
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var a02 = this.qa_1[2];
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var a03 = this.qa_1[3];
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var m000 = this.qa_1[4];
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var m001 = this.qa_1[5];
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var m002 = this.qa_1[6];
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var m003 = this.qa_1[7];
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var m100 = this.qa_1[8];
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var m101 = this.qa_1[9];
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var m102 = this.qa_1[10];
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var m103 = this.qa_1[11];
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var m200 = this.qa_1[12];
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var m201 = this.qa_1[13];
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var m202 = this.qa_1[14];
    // Inline function 'org.khronos.webgl.get' call
    // Inline function 'kotlin.js.asDynamic' call
    var m203 = this.qa_1[15];
    var b00 = a00 * m001 - a01 * m000;
    var b01 = a00 * m002 - a02 * m000;
    var b02 = a00 * m003 - a03 * m000;
    var b03 = a01 * m002 - a02 * m001;
    var b04 = a01 * m003 - a03 * m001;
    var b05 = a02 * m003 - a03 * m002;
    var b06 = m100 * m201 - m101 * m200;
    var b07 = m100 * m202 - m102 * m200;
    var b08 = m100 * m203 - m103 * m200;
    var b09 = m101 * m202 - m102 * m201;
    var m010 = m101 * m203 - m103 * m201;
    var m011 = m102 * m203 - m103 * m202;
    var det = b00 * m011 - b01 * m010 + b02 * b09 + b03 * b08 - b04 * b07 + b05 * b06;
    if (det === 0.0) {
      return this;
    }
    var invDet = 1.0 / det;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.qa_1[0] = (m001 * m011 - m002 * m010 + m003 * b09) * invDet;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.qa_1[1] = (-a01 * m011 + a02 * m010 - a03 * b09) * invDet;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.qa_1[2] = (m201 * b05 - m202 * b04 + m203 * b03) * invDet;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.qa_1[3] = (-m101 * b05 + m102 * b04 - m103 * b03) * invDet;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.qa_1[4] = (-m000 * m011 + m002 * b08 - m003 * b07) * invDet;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.qa_1[5] = (a00 * m011 - a02 * b08 + a03 * b07) * invDet;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.qa_1[6] = (-m200 * b05 + m202 * b02 - m203 * b01) * invDet;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.qa_1[7] = (m100 * b05 - m102 * b02 + m103 * b01) * invDet;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.qa_1[8] = (m000 * m010 - m001 * b08 + m003 * b06) * invDet;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.qa_1[9] = (-a00 * m010 + a01 * b08 - a03 * b06) * invDet;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.qa_1[10] = (m200 * b04 - m201 * b02 + m203 * b00) * invDet;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.qa_1[11] = (-m100 * b04 + m101 * b02 - m103 * b00) * invDet;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.qa_1[12] = (-m000 * b09 + m001 * b07 - m002 * b06) * invDet;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.qa_1[13] = (a00 * b09 - a01 * b07 + a02 * b06) * invDet;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.qa_1[14] = (-m200 * b03 + m201 * b01 - m202 * b00) * invDet;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.qa_1[15] = (m100 * b03 - m101 * b01 + m102 * b00) * invDet;
    return this;
  };
  protoOf(Mat4).ra = function (provider, property) {
    provider.fa(property.callableName, this);
    return this;
  };
  protoOf(Mat4).ta = function (provider, property) {
    return this;
  };
  protoOf(Mat4).ic = function (gl, uniformLocation, samplerIndex) {
    gl.uniformMatrix4fv(uniformLocation, false, this.qa_1);
  };
  function Mat4Array_init_$Init$(size, $this) {
    Mat4Array.call($this, null, size, size);
    return $this;
  }
  function Mat4Array_init_$Create$(size) {
    return Mat4Array_init_$Init$(size, objectCreate(protoOf(Mat4Array)));
  }
  function Mat4Array(backingStorage, startIndex, endIndex) {
    startIndex = startIndex === VOID ? 0 : startIndex;
    endIndex = endIndex === VOID ? 0 : endIndex;
    var tmp = this;
    var tmp1_elvis_lhs = backingStorage == null ? null : backingStorage.subarray(imul(startIndex, 16), imul(endIndex, 16));
    tmp.wc_1 = tmp1_elvis_lhs == null ? new Float32Array(imul(startIndex, 16)) : tmp1_elvis_lhs;
  }
  protoOf(Mat4Array).hc = function () {
    return this.wc_1;
  };
  protoOf(Mat4Array).ua = function (values) {
    var inductionVariable = 0;
    var last = this.wc_1.length;
    if (inductionVariable < last)
      do {
        var i = inductionVariable;
        inductionVariable = inductionVariable + 1 | 0;
        var tmp0 = this.wc_1;
        var tmp0_elvis_lhs = getOrNull(values, i % 16 | 0);
        // Inline function 'org.khronos.webgl.set' call
        // Inline function 'kotlin.js.asDynamic' call
        tmp0[i] = tmp0_elvis_lhs == null ? ((i % 16 | 0) % 5 | 0) === 0 ? 1.0 : 0.0 : tmp0_elvis_lhs;
      }
       while (inductionVariable < last);
    return this;
  };
  protoOf(Mat4Array).ic = function (gl, uniformLocation, samplerIndex) {
    gl.uniformMatrix4fv(uniformLocation, false, this.wc_1);
  };
  function get_UNSIGNED_INT_SAMPLER_2D(_this__u8e3s4) {
    return 36306;
  }
  function get_INT_SAMPLER_2D(_this__u8e3s4) {
    return 36298;
  }
  function get_SAMPLER_2D_SHADOW(_this__u8e3s4) {
    return 35682;
  }
  function get_UNSIGNED_INT_SAMPLER_CUBE(_this__u8e3s4) {
    return 36308;
  }
  function get_INT_SAMPLER_CUBE(_this__u8e3s4) {
    return 36300;
  }
  function get_SAMPLER_CUBE_SHADOW(_this__u8e3s4) {
    return 36293;
  }
  function get_UNSIGNED_INT_SAMPLER_3D(_this__u8e3s4) {
    return 36307;
  }
  function get_INT_SAMPLER_3D(_this__u8e3s4) {
    return 36299;
  }
  function get_SAMPLER_3D(_this__u8e3s4) {
    return 35679;
  }
  function get_UNSIGNED_INT_SAMPLER_2D_ARRAY(_this__u8e3s4) {
    return 36311;
  }
  function get_INT_SAMPLER_2D_ARRAY(_this__u8e3s4) {
    return 36303;
  }
  function get_SAMPLER_2D_ARRAY_SHADOW(_this__u8e3s4) {
    return 36292;
  }
  function get_SAMPLER_2D_ARRAY(_this__u8e3s4) {
    return 36289;
  }
  function UniformDescriptor(name, type, size, location) {
    this.xc_1 = name;
    this.yc_1 = type;
    this.zc_1 = size;
    this.ad_1 = location;
  }
  function Companion_4() {
    Companion_instance_4 = this;
    this.bd_1 = mapOf([to(getKClass(Vec1), WebGLRenderingContext.FLOAT), to(getKClass(Vec2), WebGLRenderingContext.FLOAT_VEC2), to(getKClass(Vec3), WebGLRenderingContext.FLOAT_VEC3), to(getKClass(Vec4), WebGLRenderingContext.FLOAT_VEC4), to(getKClass(Vec1Array), WebGLRenderingContext.FLOAT), to(getKClass(Vec2Array), WebGLRenderingContext.FLOAT_VEC2), to(getKClass(Vec3Array), WebGLRenderingContext.FLOAT_VEC3), to(getKClass(Vec4Array), WebGLRenderingContext.FLOAT_VEC4), to(getKClass(Mat4), WebGLRenderingContext.FLOAT_MAT4), to(getKClass(IVec1), WebGLRenderingContext.INT), to(getKClass(IVec2), WebGLRenderingContext.INT_VEC2), to(getKClass(IVec3), WebGLRenderingContext.INT_VEC3), to(getKClass(IVec4), WebGLRenderingContext.INT_VEC4), to(getKClass(Sampler2D), WebGLRenderingContext.SAMPLER_2D), to(getKClass(Sampler3D), WebGLRenderingContext.SAMPLER_2D), to(getKClass(SamplerCube), WebGLRenderingContext.SAMPLER_CUBE)]);
  }
  protoOf(Companion_4).cd = function (type, arraySize, debugName) {
    if (arraySize === 1) {
      if (type === WebGLRenderingContext.INT)
        return IVec1_init_$Create$();
      else if (type === WebGLRenderingContext.UNSIGNED_INT)
        return IVec1_init_$Create$();
      else if (type === WebGLRenderingContext.INT_VEC2)
        return IVec2_init_$Create$();
      else if (type === WebGLRenderingContext.INT_VEC3)
        return IVec3_init_$Create$();
      else if (type === WebGLRenderingContext.INT_VEC4)
        return IVec4_init_$Create$();
      else if (type === WebGLRenderingContext.FLOAT)
        return Vec1_init_$Create$();
      else if (type === WebGLRenderingContext.FLOAT_VEC2)
        return Vec2_init_$Create$();
      else if (type === WebGLRenderingContext.FLOAT_VEC3)
        return Vec3_init_$Create$();
      else if (type === WebGLRenderingContext.FLOAT_VEC4)
        return Vec4_init_$Create$();
      else if (type === WebGLRenderingContext.FLOAT_MAT4)
        return Mat4_init_$Create$(new Float32Array([]));
      else if (type === get_UNSIGNED_INT_SAMPLER_2D(WebGLRenderingContext) || type === WebGLRenderingContext.SAMPLER_2D)
        return new Sampler2D(debugName);
      else if (type === WebGLRenderingContext.SAMPLER_CUBE)
        return new SamplerCube(debugName);
      else if (type === get_SAMPLER_3D(WebGLRenderingContext))
        return new Sampler3D(debugName);
    } else {
      if (type === WebGLRenderingContext.INT)
        return IVec1Array_init_$Create$(arraySize);
      else if (type === WebGLRenderingContext.UNSIGNED_INT)
        return IVec1Array_init_$Create$(arraySize);
      else if (type === WebGLRenderingContext.FLOAT)
        return Vec1Array_init_$Create$(arraySize);
      else if (type === WebGLRenderingContext.FLOAT_VEC2)
        return Vec2Array_init_$Create$(arraySize);
      else if (type === WebGLRenderingContext.FLOAT_VEC3)
        return Vec3Array_init_$Create$(arraySize);
      else if (type === WebGLRenderingContext.FLOAT_VEC4)
        return Vec4Array_init_$Create$(arraySize);
      else if (type === WebGLRenderingContext.FLOAT_MAT4)
        return Mat4Array_init_$Create$(arraySize);
    }
    throw Error_init_$Create$('Unhandled uniform variable of type ID ' + type + '.');
  };
  protoOf(Companion_4).dd = function (type) {
    return type === WebGLRenderingContext.SAMPLER_2D || type === get_SAMPLER_3D(WebGLRenderingContext) || type === WebGLRenderingContext.SAMPLER_CUBE || type === get_SAMPLER_2D_SHADOW(WebGLRenderingContext) || type === get_SAMPLER_2D_ARRAY(WebGLRenderingContext) || type === get_SAMPLER_2D_ARRAY_SHADOW(WebGLRenderingContext) || type === get_SAMPLER_CUBE_SHADOW(WebGLRenderingContext) || type === get_INT_SAMPLER_2D(WebGLRenderingContext) || type === get_INT_SAMPLER_3D(WebGLRenderingContext) || type === get_INT_SAMPLER_CUBE(WebGLRenderingContext) || type === get_INT_SAMPLER_2D_ARRAY(WebGLRenderingContext) || type === get_UNSIGNED_INT_SAMPLER_2D(WebGLRenderingContext) || type === get_UNSIGNED_INT_SAMPLER_3D(WebGLRenderingContext) || type === get_UNSIGNED_INT_SAMPLER_CUBE(WebGLRenderingContext) || type === get_UNSIGNED_INT_SAMPLER_2D_ARRAY(WebGLRenderingContext);
  };
  var Companion_instance_4;
  function Companion_getInstance_4() {
    if (Companion_instance_4 == null)
      new Companion_4();
    return Companion_instance_4;
  }
  function ProgramReflection(gl, glProgram) {
    Companion_getInstance_4();
    Drawable.call(this);
    this.ed_1 = gl;
    this.fd_1 = glProgram;
    this.gd_1 = HashMap_init_$Create$();
    var tmp = this.ed_1.getProgramParameter(this.fd_1, WebGLRenderingContext.ACTIVE_UNIFORMS);
    var nUniforms = (!(tmp == null) ? typeof tmp === 'number' : false) ? tmp : THROW_CCE();
    var inductionVariable = 0;
    if (inductionVariable < nUniforms)
      $l$loop_0: do {
        var i = inductionVariable;
        inductionVariable = inductionVariable + 1 | 0;
        var tmp0_elvis_lhs = this.ed_1.getActiveUniform(this.fd_1, i);
        var tmp_0;
        if (tmp0_elvis_lhs == null) {
          throw Error_init_$Create$('Uniform listed by gl.getProgramParameter but not found by gl.getActiveUniform.');
        } else {
          tmp_0 = tmp0_elvis_lhs;
        }
        var glUniform = tmp_0;
        var nameParts = split(glUniform.name, ['.']);
        var tmp1_elvis_lhs = lastOrNull(nameParts);
        var tmp_1;
        if (tmp1_elvis_lhs == null) {
          continue $l$loop_0;
        } else {
          tmp_1 = tmp1_elvis_lhs;
        }
        var uniformName = tmp_1;
        var tmp2_elvis_lhs = lastOrNull(dropLast(nameParts, 1));
        var tmp_2;
        if (tmp2_elvis_lhs == null) {
          continue $l$loop_0;
        } else {
          tmp_2 = tmp2_elvis_lhs;
        }
        var structName = tmp_2;
        var tmp0 = this.gd_1;
        var tmp3_elvis_lhs = this.gd_1.x(structName);
        // Inline function 'kotlin.collections.set' call
        var value = tmp3_elvis_lhs == null ? ArrayList_init_$Create$() : tmp3_elvis_lhs;
        tmp0.e2(structName, value);
        var tmp4_safe_receiver = this.gd_1.x(structName);
        var tmp_3;
        if (tmp4_safe_receiver == null) {
          tmp_3 = null;
        } else {
          var tmp_4 = glUniform.type;
          var tmp_5 = glUniform.size;
          var tmp5_elvis_lhs = this.ed_1.getUniformLocation(this.fd_1, glUniform.name);
          var tmp_6;
          if (tmp5_elvis_lhs == null) {
            throw Error_init_$Create$('No location for ' + glUniform.name);
          } else {
            tmp_6 = tmp5_elvis_lhs;
          }
          tmp_3 = tmp4_safe_receiver.d(new UniformDescriptor(uniformName, tmp_4, tmp_5, tmp_6));
        }
        if (tmp_3 == null)
          throw Error_init_$Create$('Struct name ' + structName + ' missing from uniform descriptors.');
      }
       while (inductionVariable < nUniforms);
  }
  protoOf(ProgramReflection).ga = function (target) {
    var indexedObject = target.ba_1;
    var inductionVariable = 0;
    var last = indexedObject.length;
    $l$loop: while (inductionVariable < last) {
      var structName = indexedObject[inductionVariable];
      inductionVariable = inductionVariable + 1 | 0;
      var tmp0_elvis_lhs = this.gd_1.x(structName);
      var tmp;
      if (tmp0_elvis_lhs == null) {
        continue $l$loop;
      } else {
        tmp = tmp0_elvis_lhs;
      }
      var descList = tmp;
      var _iterator__ex2g4s = descList.f();
      while (_iterator__ex2g4s.g()) {
        var uniformDesc = _iterator__ex2g4s.h();
        var reflectionVariable = Companion_getInstance_4().cd(uniformDesc.yc_1, uniformDesc.zc_1, uniformDesc.xc_1);
        if (target.ca_1.w(uniformDesc.xc_1)) {
          var tmp1_elvis_lhs = target.ca_1.x(uniformDesc.xc_1);
          var tmp_0;
          if (tmp1_elvis_lhs == null) {
            throw Error_init_$Create$('Key "' + uniformDesc.xc_1 + '" exists in uniforms, but its value is null.');
          } else {
            tmp_0 = tmp1_elvis_lhs;
          }
          var existingVariable = tmp_0;
          if (!(Companion_getInstance_4().bd_1.x(getKClassFromExpression(existingVariable)) == Companion_getInstance_4().bd_1.x(getKClassFromExpression(reflectionVariable))) || !(existingVariable.jc() === reflectionVariable.jc())) {
            throw Error_init_$Create$('Trying to reflect uniform ' + uniformDesc.xc_1 + ' as a ' + getKClassFromExpression(reflectionVariable).x4() + ' with element count ' + reflectionVariable.jc() + ', but it already exists in the target object as a ' + getKClassFromExpression(existingVariable).x4() + ' with element count ' + existingVariable.jc() + '.');
          }
        } else {
          var tmp0 = target.ca_1;
          // Inline function 'kotlin.collections.set' call
          var key = uniformDesc.xc_1;
          tmp0.e2(key, reflectionVariable);
        }
      }
    }
  };
  protoOf(ProgramReflection).ha = function (uniformProviders) {
    this.ed_1.useProgram(this.fd_1);
    var textureUnitCount = 0;
    var inductionVariable = 0;
    var last = uniformProviders.length;
    while (inductionVariable < last) {
      var provider = uniformProviders[inductionVariable];
      inductionVariable = inductionVariable + 1 | 0;
      var indexedObject = provider.ba_1;
      var inductionVariable_0 = 0;
      var last_0 = indexedObject.length;
      $l$loop: while (inductionVariable_0 < last_0) {
        var structName = indexedObject[inductionVariable_0];
        inductionVariable_0 = inductionVariable_0 + 1 | 0;
        var tmp0_elvis_lhs = this.gd_1.x(structName);
        var tmp;
        if (tmp0_elvis_lhs == null) {
          continue $l$loop;
        } else {
          tmp = tmp0_elvis_lhs;
        }
        var descList = tmp;
        var _iterator__ex2g4s = descList.f();
        while (_iterator__ex2g4s.g()) {
          var uniformDesc = _iterator__ex2g4s.h();
          var tmp1_safe_receiver = provider.y6(uniformDesc.xc_1);
          var tmp_0;
          if (tmp1_safe_receiver == null) {
            tmp_0 = null;
          } else {
            tmp1_safe_receiver.ic(this.ed_1, uniformDesc.ad_1, textureUnitCount);
            tmp_0 = Unit_instance;
          }
          if (tmp_0 == null)
            throw Error_init_$Create$('Uniform ' + uniformDesc.xc_1 + ' in struct ' + structName + ' from list ' + descList.toString());
          if (Companion_getInstance_4().dd(uniformDesc.yc_1)) {
            textureUnitCount = textureUnitCount + uniformDesc.zc_1 | 0;
          }
        }
      }
    }
  };
  function Sampler2D(debugName) {
    debugName = debugName === VOID ? '' : debugName;
    this.hd_1 = debugName;
    this.id_1 = new Int32Array(1);
    var tmp = this;
    var tmp_0 = 0;
    // Inline function 'kotlin.arrayOfNulls' call
    var tmp_1 = Array(1);
    while (tmp_0 < 1) {
      tmp_1[tmp_0] = null;
      tmp_0 = tmp_0 + 1 | 0;
    }
    tmp.jd_1 = tmp_1;
  }
  protoOf(Sampler2D).hc = function () {
    return this.id_1;
  };
  protoOf(Sampler2D).ic = function (gl, uniformLocation, samplerIndex) {
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.id_1[0] = samplerIndex;
    if (!(this.jd_1[0] == null)) {
      gl.uniform1iv(uniformLocation, this.id_1);
      gl.activeTexture(WebGLRenderingContext.TEXTURE0 + samplerIndex | 0);
      gl.bindTexture(WebGLRenderingContext.TEXTURE_2D, this.jd_1[0]);
    } else {
      console.warn("No texture bound to Sampler2D reflecting uniform '" + this.hd_1 + "'.");
    }
  };
  function get_TEXTURE_3D(_this__u8e3s4) {
    return 32879;
  }
  function Sampler3D(debugName) {
    debugName = debugName === VOID ? '' : debugName;
    this.kd_1 = debugName;
    this.ld_1 = new Int32Array(1);
    var tmp = this;
    var tmp_0 = 0;
    // Inline function 'kotlin.arrayOfNulls' call
    var tmp_1 = Array(1);
    while (tmp_0 < 1) {
      tmp_1[tmp_0] = null;
      tmp_0 = tmp_0 + 1 | 0;
    }
    tmp.md_1 = tmp_1;
  }
  protoOf(Sampler3D).hc = function () {
    return this.ld_1;
  };
  protoOf(Sampler3D).ic = function (gl, uniformLocation, samplerIndex) {
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.ld_1[0] = samplerIndex;
    if (!(this.md_1[0] == null)) {
      gl.uniform1iv(uniformLocation, this.ld_1);
      gl.activeTexture(WebGLRenderingContext.TEXTURE0 + samplerIndex | 0);
      gl.bindTexture(get_TEXTURE_3D(WebGLRenderingContext), this.md_1[0]);
    } else {
      console.warn("No texture bound to Sampler3D reflecting uniform '" + this.kd_1 + "'.");
    }
  };
  function SamplerCube(debugName) {
    debugName = debugName === VOID ? '' : debugName;
    this.nd_1 = debugName;
    this.od_1 = new Int32Array(1);
    var tmp = this;
    var tmp_0 = 0;
    // Inline function 'kotlin.arrayOfNulls' call
    var tmp_1 = Array(1);
    while (tmp_0 < 1) {
      tmp_1[tmp_0] = null;
      tmp_0 = tmp_0 + 1 | 0;
    }
    tmp.pd_1 = tmp_1;
  }
  protoOf(SamplerCube).hc = function () {
    return this.od_1;
  };
  protoOf(SamplerCube).ic = function (gl, uniformLocation, samplerIndex) {
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    this.od_1[0] = samplerIndex;
    if (!(this.pd_1[0] == null)) {
      gl.uniform1iv(uniformLocation, this.od_1);
      gl.activeTexture(WebGLRenderingContext.TEXTURE0 + samplerIndex | 0);
      gl.bindTexture(WebGLRenderingContext.TEXTURE_CUBE_MAP, this.pd_1[0]);
    } else {
      console.warn("No texture bound to Sampler2D reflecting uniform '" + this.nd_1 + "'.");
    }
  };
  function Uniform() {
  }
  function UniformFloat() {
  }
  function UniformInt() {
  }
  function UniformSampler() {
  }
  function UniformProvider(glslStructNames) {
    Drawable.call(this);
    this.ba_1 = glslStructNames;
    this.ca_1 = HashMap_init_$Create$();
    this.da_1 = ArrayList_init_$Create$();
  }
  protoOf(UniformProvider).fa = function (uniformName, uniform) {
    // Inline function 'kotlin.collections.set' call
    this.ca_1.e2(uniformName, uniform);
  };
  protoOf(UniformProvider).y6 = function (name) {
    if (!this.ca_1.w(name)) {
      console.error("WARNING: Attempt to access unknown or non-active uniform '" + name + "'.");
    }
    return this.ca_1.x(name);
  };
  protoOf(UniformProvider).ea = function (children) {
    addAll(this.da_1, children);
    this.ga(this);
  };
  protoOf(UniformProvider).ga = function (target) {
    // Inline function 'kotlin.collections.forEach' call
    var _iterator__ex2g4s = this.da_1.f();
    while (_iterator__ex2g4s.g()) {
      var element = _iterator__ex2g4s.h();
      element.ga(target);
    }
  };
  protoOf(UniformProvider).ha = function (uniformProviders) {
    // Inline function 'kotlin.collections.forEach' call
    var _iterator__ex2g4s = this.da_1.f();
    while (_iterator__ex2g4s.g()) {
      var element = _iterator__ex2g4s.h();
      element.ha(arrayConcat([[this], uniformProviders]));
    }
  };
  function Vec1_init_$Init$(u, $this) {
    u = u === VOID ? 0.0 : u;
    Vec1.call($this, null, 0);
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    $this.qd_1[0] = u;
    return $this;
  }
  function Vec1_init_$Create$(u) {
    return Vec1_init_$Init$(u, objectCreate(protoOf(Vec1)));
  }
  function Companion_5() {
    Companion_instance_5 = this;
    this.rd_1 = Vec1_init_$Create$();
    this.sd_1 = Vec1_init_$Create$(1.0);
  }
  var Companion_instance_5;
  function Companion_getInstance_5() {
    if (Companion_instance_5 == null)
      new Companion_5();
    return Companion_instance_5;
  }
  function Vec1(backingStorage, offset) {
    Companion_getInstance_5();
    offset = offset === VOID ? 0 : offset;
    var tmp = this;
    var tmp1_elvis_lhs = backingStorage == null ? null : backingStorage.subarray(offset, offset + 1 | 0);
    tmp.qd_1 = tmp1_elvis_lhs == null ? new Float32Array(1) : tmp1_elvis_lhs;
  }
  protoOf(Vec1).hc = function () {
    return this.qd_1;
  };
  protoOf(Vec1).ua = function (values) {
    var tmp0 = this.qd_1;
    // Inline function 'kotlin.collections.getOrElse' call
    var tmp;
    if (0 <= 0 ? 0 <= (values.length - 1 | 0) : false) {
      tmp = values[0];
    } else {
      tmp = 0.0;
    }
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    tmp0[0] = tmp;
    return this;
  };
  protoOf(Vec1).ic = function (gl, uniformLocation, samplerIndex) {
    gl.uniform1fv(uniformLocation, this.qd_1);
  };
  function Vec1Array_init_$Init$(size, $this) {
    Vec1Array.call($this, null, size, size);
    return $this;
  }
  function Vec1Array_init_$Create$(size) {
    return Vec1Array_init_$Init$(size, objectCreate(protoOf(Vec1Array)));
  }
  function Vec1Array(backingStorage, startIndex, endIndex) {
    startIndex = startIndex === VOID ? 0 : startIndex;
    endIndex = endIndex === VOID ? 0 : endIndex;
    VecArray.call(this);
    var tmp = this;
    var tmp1_elvis_lhs = backingStorage == null ? null : backingStorage.subarray(startIndex, endIndex);
    tmp.td_1 = tmp1_elvis_lhs == null ? new Float32Array(startIndex) : tmp1_elvis_lhs;
  }
  protoOf(Vec1Array).hc = function () {
    return this.td_1;
  };
  protoOf(Vec1Array).ua = function (values) {
    var inductionVariable = 0;
    var last = this.td_1.length;
    if (inductionVariable < last)
      do {
        var i = inductionVariable;
        inductionVariable = inductionVariable + 1 | 0;
        var tmp0 = this.td_1;
        var tmp0_elvis_lhs = getOrNull(values, i);
        // Inline function 'org.khronos.webgl.set' call
        // Inline function 'kotlin.js.asDynamic' call
        tmp0[i] = tmp0_elvis_lhs == null ? 0.0 : tmp0_elvis_lhs;
      }
       while (inductionVariable < last);
    return this;
  };
  protoOf(Vec1Array).ic = function (gl, uniformLocation, samplerIndex) {
    gl.uniform1fv(uniformLocation, this.td_1);
  };
  function Vec2_init_$Init$(u, v, $this) {
    u = u === VOID ? 0.0 : u;
    v = v === VOID ? 0.0 : v;
    Vec2.call($this, null, 0);
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    $this.bb_1[0] = u;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    $this.bb_1[1] = v;
    return $this;
  }
  function Vec2_init_$Create$(u, v) {
    return Vec2_init_$Init$(u, v, objectCreate(protoOf(Vec2)));
  }
  function Companion_6() {
    Companion_instance_6 = this;
    this.ud_1 = Vec2_init_$Create$();
    this.vd_1 = Vec2_init_$Create$(1.0, 1.0);
  }
  var Companion_instance_6;
  function Companion_getInstance_6() {
    if (Companion_instance_6 == null)
      new Companion_6();
    return Companion_instance_6;
  }
  function Vec2(backingStorage, offset) {
    Companion_getInstance_6();
    offset = offset === VOID ? 0 : offset;
    var tmp = this;
    var tmp1_elvis_lhs = backingStorage == null ? null : backingStorage.subarray(offset, offset + 2 | 0);
    tmp.bb_1 = tmp1_elvis_lhs == null ? new Float32Array(2) : tmp1_elvis_lhs;
  }
  protoOf(Vec2).hc = function () {
    return this.bb_1;
  };
  protoOf(Vec2).ua = function (values) {
    var tmp0 = this.bb_1;
    // Inline function 'kotlin.collections.getOrElse' call
    var tmp;
    if (0 <= 0 ? 0 <= (values.length - 1 | 0) : false) {
      tmp = values[0];
    } else {
      tmp = 0.0;
    }
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    tmp0[0] = tmp;
    var tmp0_0 = this.bb_1;
    // Inline function 'kotlin.collections.getOrElse' call
    var tmp_0;
    if (0 <= 1 ? 1 <= (values.length - 1 | 0) : false) {
      tmp_0 = values[1];
    } else {
      tmp_0 = 0.0;
    }
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    tmp0_0[1] = tmp_0;
    return this;
  };
  protoOf(Vec2).ic = function (gl, uniformLocation, samplerIndex) {
    gl.uniform2fv(uniformLocation, this.bb_1);
  };
  function Vec2Array_init_$Init$(size, $this) {
    Vec2Array.call($this, null, size, size);
    return $this;
  }
  function Vec2Array_init_$Create$(size) {
    return Vec2Array_init_$Init$(size, objectCreate(protoOf(Vec2Array)));
  }
  function Vec2Array(backingStorage, startIndex, endIndex) {
    startIndex = startIndex === VOID ? 0 : startIndex;
    endIndex = endIndex === VOID ? 0 : endIndex;
    VecArray.call(this);
    var tmp = this;
    var tmp1_elvis_lhs = backingStorage == null ? null : backingStorage.subarray(imul(startIndex, 2), imul(endIndex, 2));
    tmp.wd_1 = tmp1_elvis_lhs == null ? new Float32Array(imul(startIndex, 2)) : tmp1_elvis_lhs;
  }
  protoOf(Vec2Array).hc = function () {
    return this.wd_1;
  };
  protoOf(Vec2Array).ua = function (values) {
    var inductionVariable = 0;
    var last = this.wd_1.length;
    if (inductionVariable < last)
      do {
        var i = inductionVariable;
        inductionVariable = inductionVariable + 1 | 0;
        var tmp0 = this.wd_1;
        var tmp0_elvis_lhs = getOrNull(values, i % 2 | 0);
        // Inline function 'org.khronos.webgl.set' call
        // Inline function 'kotlin.js.asDynamic' call
        tmp0[i] = tmp0_elvis_lhs == null ? 0.0 : tmp0_elvis_lhs;
      }
       while (inductionVariable < last);
    return this;
  };
  protoOf(Vec2Array).ic = function (gl, uniformLocation, samplerIndex) {
    gl.uniform4fv(uniformLocation, this.wd_1);
  };
  function Vec3_init_$Init$(u, v, s, $this) {
    u = u === VOID ? 0.0 : u;
    v = v === VOID ? 0.0 : v;
    s = s === VOID ? 0.0 : s;
    Vec3.call($this, null, 0);
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    $this.xd_1[0] = u;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    $this.xd_1[1] = v;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    $this.xd_1[2] = s;
    return $this;
  }
  function Vec3_init_$Create$(u, v, s) {
    return Vec3_init_$Init$(u, v, s, objectCreate(protoOf(Vec3)));
  }
  function Companion_7() {
    Companion_instance_7 = this;
    this.yd_1 = Vec3_init_$Create$();
    this.zd_1 = Vec3_init_$Create$(1.0, 1.0, 1.0);
  }
  var Companion_instance_7;
  function Companion_getInstance_7() {
    if (Companion_instance_7 == null)
      new Companion_7();
    return Companion_instance_7;
  }
  function Vec3(backingStorage, offset) {
    Companion_getInstance_7();
    offset = offset === VOID ? 0 : offset;
    var tmp = this;
    var tmp1_elvis_lhs = backingStorage == null ? null : backingStorage.subarray(offset, offset + 3 | 0);
    tmp.xd_1 = tmp1_elvis_lhs == null ? new Float32Array(3) : tmp1_elvis_lhs;
  }
  protoOf(Vec3).hc = function () {
    return this.xd_1;
  };
  protoOf(Vec3).ua = function (values) {
    var tmp0 = this.xd_1;
    // Inline function 'kotlin.collections.getOrElse' call
    var tmp;
    if (0 <= 0 ? 0 <= (values.length - 1 | 0) : false) {
      tmp = values[0];
    } else {
      tmp = 0.0;
    }
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    tmp0[0] = tmp;
    var tmp0_0 = this.xd_1;
    // Inline function 'kotlin.collections.getOrElse' call
    var tmp_0;
    if (0 <= 1 ? 1 <= (values.length - 1 | 0) : false) {
      tmp_0 = values[1];
    } else {
      tmp_0 = 0.0;
    }
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    tmp0_0[1] = tmp_0;
    var tmp0_1 = this.xd_1;
    // Inline function 'kotlin.collections.getOrElse' call
    var tmp_1;
    if (0 <= 2 ? 2 <= (values.length - 1 | 0) : false) {
      tmp_1 = values[2];
    } else {
      tmp_1 = 0.0;
    }
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    tmp0_1[2] = tmp_1;
    return this;
  };
  protoOf(Vec3).ic = function (gl, uniformLocation, samplerIndex) {
    gl.uniform3fv(uniformLocation, this.xd_1);
  };
  function Vec3Array_init_$Init$(size, $this) {
    Vec3Array.call($this, null, size, size);
    return $this;
  }
  function Vec3Array_init_$Create$(size) {
    return Vec3Array_init_$Init$(size, objectCreate(protoOf(Vec3Array)));
  }
  function Vec3Array(backingStorage, startIndex, endIndex) {
    startIndex = startIndex === VOID ? 0 : startIndex;
    endIndex = endIndex === VOID ? 0 : endIndex;
    VecArray.call(this);
    var tmp = this;
    var tmp1_elvis_lhs = backingStorage == null ? null : backingStorage.subarray(imul(startIndex, 3), imul(endIndex, 3));
    tmp.ae_1 = tmp1_elvis_lhs == null ? new Float32Array(imul(startIndex, 3)) : tmp1_elvis_lhs;
  }
  protoOf(Vec3Array).hc = function () {
    return this.ae_1;
  };
  protoOf(Vec3Array).ua = function (values) {
    var inductionVariable = 0;
    var last = this.ae_1.length;
    if (inductionVariable < last)
      do {
        var i = inductionVariable;
        inductionVariable = inductionVariable + 1 | 0;
        var tmp0 = this.ae_1;
        var tmp0_elvis_lhs = getOrNull(values, i % 3 | 0);
        // Inline function 'org.khronos.webgl.set' call
        // Inline function 'kotlin.js.asDynamic' call
        tmp0[i] = tmp0_elvis_lhs == null ? 0.0 : tmp0_elvis_lhs;
      }
       while (inductionVariable < last);
    return this;
  };
  protoOf(Vec3Array).ic = function (gl, uniformLocation, samplerIndex) {
    gl.uniform3fv(uniformLocation, this.ae_1);
  };
  function Vec4_init_$Init$(u, v, s, t, $this) {
    u = u === VOID ? 0.0 : u;
    v = v === VOID ? 0.0 : v;
    s = s === VOID ? 0.0 : s;
    t = t === VOID ? 1.0 : t;
    Vec4.call($this, null, 0);
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    $this.be_1[0] = u;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    $this.be_1[1] = v;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    $this.be_1[2] = s;
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    $this.be_1[3] = t;
    return $this;
  }
  function Vec4_init_$Create$(u, v, s, t) {
    return Vec4_init_$Init$(u, v, s, t, objectCreate(protoOf(Vec4)));
  }
  function Companion_8() {
    Companion_instance_8 = this;
    this.ce_1 = Vec4_init_$Create$(0.0, 0.0, 0.0, 0.0);
    this.de_1 = Vec4_init_$Create$(1.0, 1.0, 1.0, 1.0);
  }
  var Companion_instance_8;
  function Companion_getInstance_8() {
    if (Companion_instance_8 == null)
      new Companion_8();
    return Companion_instance_8;
  }
  function Vec4(backingStorage, offset) {
    Companion_getInstance_8();
    offset = offset === VOID ? 0 : offset;
    var tmp = this;
    var tmp1_elvis_lhs = backingStorage == null ? null : backingStorage.subarray(offset, offset + 4 | 0);
    tmp.be_1 = tmp1_elvis_lhs == null ? new Float32Array(4) : tmp1_elvis_lhs;
  }
  protoOf(Vec4).hc = function () {
    return this.be_1;
  };
  protoOf(Vec4).ua = function (values) {
    var tmp0 = this.be_1;
    // Inline function 'kotlin.collections.getOrElse' call
    var tmp;
    if (0 <= 0 ? 0 <= (values.length - 1 | 0) : false) {
      tmp = values[0];
    } else {
      tmp = 0.0;
    }
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    tmp0[0] = tmp;
    var tmp0_0 = this.be_1;
    // Inline function 'kotlin.collections.getOrElse' call
    var tmp_0;
    if (0 <= 1 ? 1 <= (values.length - 1 | 0) : false) {
      tmp_0 = values[1];
    } else {
      tmp_0 = 0.0;
    }
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    tmp0_0[1] = tmp_0;
    var tmp0_1 = this.be_1;
    // Inline function 'kotlin.collections.getOrElse' call
    var tmp_1;
    if (0 <= 2 ? 2 <= (values.length - 1 | 0) : false) {
      tmp_1 = values[2];
    } else {
      tmp_1 = 0.0;
    }
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    tmp0_1[2] = tmp_1;
    var tmp0_2 = this.be_1;
    // Inline function 'kotlin.collections.getOrElse' call
    var tmp_2;
    if (0 <= 3 ? 3 <= (values.length - 1 | 0) : false) {
      tmp_2 = values[3];
    } else {
      tmp_2 = 1.0;
    }
    // Inline function 'org.khronos.webgl.set' call
    // Inline function 'kotlin.js.asDynamic' call
    tmp0_2[3] = tmp_2;
    return this;
  };
  protoOf(Vec4).ic = function (gl, uniformLocation, samplerIndex) {
    gl.uniform4fv(uniformLocation, this.be_1);
  };
  function Vec4Array_init_$Init$(size, $this) {
    Vec4Array.call($this, null, size, size);
    return $this;
  }
  function Vec4Array_init_$Create$(size) {
    return Vec4Array_init_$Init$(size, objectCreate(protoOf(Vec4Array)));
  }
  function Vec4Array(backingStorage, startIndex, endIndex) {
    startIndex = startIndex === VOID ? 0 : startIndex;
    endIndex = endIndex === VOID ? 0 : endIndex;
    VecArray.call(this);
    var tmp = this;
    var tmp1_elvis_lhs = backingStorage == null ? null : backingStorage.subarray(imul(startIndex, 4), imul(endIndex, 4));
    tmp.ee_1 = tmp1_elvis_lhs == null ? new Float32Array(imul(startIndex, 4)) : tmp1_elvis_lhs;
  }
  protoOf(Vec4Array).hc = function () {
    return this.ee_1;
  };
  protoOf(Vec4Array).ua = function (values) {
    var inductionVariable = 0;
    var last = this.ee_1.length;
    if (inductionVariable < last)
      do {
        var i = inductionVariable;
        inductionVariable = inductionVariable + 1 | 0;
        var tmp0 = this.ee_1;
        var tmp0_elvis_lhs = getOrNull(values, i % 4 | 0);
        // Inline function 'org.khronos.webgl.set' call
        // Inline function 'kotlin.js.asDynamic' call
        tmp0[i] = tmp0_elvis_lhs == null ? (i % 4 | 0) === 3 ? 1.0 : 0.0 : tmp0_elvis_lhs;
      }
       while (inductionVariable < last);
    return this;
  };
  protoOf(Vec4Array).ic = function (gl, uniformLocation, samplerIndex) {
    gl.uniform4fv(uniformLocation, this.ee_1);
  };
  function VecArray() {
  }
  //region block: post-declaration
  protoOf(IVec1).yb = commit$default;
  protoOf(IVec1).jc = getStorageSize;
  protoOf(IVec1).ua = set;
  protoOf(IVecArray).jc = getStorageSize;
  protoOf(IVecArray).ua = set;
  protoOf(IVecArray).yb = commit$default;
  protoOf(IVec2).yb = commit$default;
  protoOf(IVec2).jc = getStorageSize;
  protoOf(IVec2).ua = set;
  protoOf(IVec3).yb = commit$default;
  protoOf(IVec3).jc = getStorageSize;
  protoOf(IVec3).ua = set;
  protoOf(IVec4).yb = commit$default;
  protoOf(IVec4).jc = getStorageSize;
  protoOf(IVec4).ua = set;
  protoOf(Mat4).yb = commit$default;
  protoOf(Mat4).jc = getStorageSize_0;
  protoOf(Mat4Array).yb = commit$default;
  protoOf(Mat4Array).jc = getStorageSize_0;
  protoOf(Sampler2D).yb = commit$default;
  protoOf(Sampler2D).jc = getStorageSize_1;
  protoOf(Sampler2D).ua = set_0;
  protoOf(Sampler3D).yb = commit$default;
  protoOf(Sampler3D).jc = getStorageSize_1;
  protoOf(Sampler3D).ua = set_0;
  protoOf(SamplerCube).yb = commit$default;
  protoOf(SamplerCube).jc = getStorageSize_1;
  protoOf(SamplerCube).ua = set_0;
  protoOf(Vec1).yb = commit$default;
  protoOf(Vec1).jc = getStorageSize_0;
  protoOf(VecArray).jc = getStorageSize_0;
  protoOf(VecArray).yb = commit$default;
  protoOf(Vec2).yb = commit$default;
  protoOf(Vec2).jc = getStorageSize_0;
  protoOf(Vec3).yb = commit$default;
  protoOf(Vec3).jc = getStorageSize_0;
  protoOf(Vec4).yb = commit$default;
  protoOf(Vec4).jc = getStorageSize_0;
  //endregion
  mainWrapper();
  return _;
}));

//# sourceMappingURL=kog-client.js.map
