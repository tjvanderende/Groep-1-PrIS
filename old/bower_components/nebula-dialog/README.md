[![Published on webcomponents.org](https://img.shields.io/badge/webcomponents.org-published-blue.svg)](https://www.webcomponents.org/element/arsnebula/nebula-dialog)
[![Gitter chat](https://badges.gitter.im/org.png)](https://gitter.im/arsnebula/webcomponents)

[![Build Status](https://saucelabs.com/browser-matrix/arsnebula.svg)](https://saucelabs.com/beta/builds/cfe7b962114d42faa30844eea0a7155e)

# \<nebula-dialog\>

A web component to display modal dialogs.

* Fixed position full-screen overlay with a backdrop and centered layout
* Built-in animation
* Declare a dialog in markup, or use the included behavior/stylesheet to build your own
* Easily styled with style attributes or with CSS variables and mixins

> Warning: This element requires browser support for [Promise](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Promise). To ensure support by all browsers, use a promise polyfill such as [PolymerLabs Promise Polyfill](https://github.com/PolymerLabs/promise-polyfill).

## Installation

```sh
$ bower install arsnebula/nebula-dialog
```

## Usage

Import the package element:

```html
<link rel="import" href="/bower_components/nebula-loader/nebula-dialog.html"> 
```

To define a custom dialog with markup, use the `nebula-dialog` element:

```html
<nebula-dialog
  opened="{{opened}}"
  result="{{result}}"
  on-opened="_onOpened"
  on-closed="_onClosed">
</nebula-dialog>
```

To create a custom dialog element, use the `nebula-dialog-behavior` and `nebula-dialog-styles` building blocks:

```html
<dom-module id="my-dialog">
  <template>
    <style include="nebula-dialog-styles"></style>
    <slot></slot>
  </template >
  <script>
    Polymer({
      is: 'my-dialog',
      behaviors: [
        Nebula.DialogBehavior
      ]
    })
  </script>
</dom-module>
```

*For more information on element properties and methods see the element API documentation.*

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

## Change Log

See [CHANGELOG](/CHANGELOG.md)

## License

See [LICENSE](/LICENSE.md)