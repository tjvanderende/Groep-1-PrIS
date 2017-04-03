var pkg = require('./package.json');
var argv = require('minimist')(process.argv.slice(2));
var build = pkg.name + ' v' + pkg.version + ' (' + Math.round(new Date().getTime() / 1000) + ')'

console.log('BUILD: ' + build)

module.exports = {
  verbose: false,
  expanded: true,
  persistent: argv.persistent || false,
  simpleOutput: true,
  plugins: {
    local: {
      disabled: true,
      browsers: ['chrome', 'firefox', 'ie']
    },
    sauce: {
      disabled: true,
      name: pkg.name,
      build: build,
      browsers: [{
        browserName: 'chrome',
        platform: 'Windows 10',
        version: 'latest-2'
      }, {
        browserName: 'firefox',
        platform: 'Windows 10',
        version: 'latest-2'
      }, {
        browserName: 'microsoftedge',
        platform: 'Windows 10',
        version: '14'
      }, {
        browserName: 'internet explorer',
        platform: 'Windows 8.1',
        version: '11'
      }, {
        browserName: 'safari',
        platform: 'macOS 10.12',
        version: '10'
      }, {
        browserName: 'safari',
        platform: 'OS X 10.11',
        version: '9'
      }]
    }
  }
}
