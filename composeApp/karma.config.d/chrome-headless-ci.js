const os = require('node:os');
const path = require('node:path');
const fs = require('node:fs');

process.env.CHROME_BIN = process.env.CHROME_BIN || '/usr/bin/google-chrome-stable';
process.env.XDG_RUNTIME_DIR = process.env.XDG_RUNTIME_DIR || fs.mkdtempSync(path.join(os.tmpdir(), 'canimation-xdg-'));

function ensureDirectory(directory) {
  try {
    fs.mkdirSync(directory, { recursive: true });
  } catch (error) {
    if (error.code !== 'EEXIST') {
      throw error;
    }
  }
}

ensureDirectory(process.env.XDG_RUNTIME_DIR);

const chromeHomeDirectory = fs.mkdtempSync(path.join(os.tmpdir(), 'canimation-chrome-home-'));
const chromeUserDataDirectory = fs.mkdtempSync(path.join(os.tmpdir(), 'canimation-chrome-user-data-'));

process.env.HOME = process.env.HOME || chromeHomeDirectory;
process.env.DBUS_SESSION_BUS_ADDRESS = process.env.DBUS_SESSION_BUS_ADDRESS || 'disabled:canimation';

config.set({
  customLaunchers: {
    ChromeHeadlessCanimationCI: {
      base: 'ChromeHeadless',
      flags: [
        '--no-sandbox',
        '--disable-dev-shm-usage',
        '--disable-setuid-sandbox',
        `--user-data-dir=${chromeUserDataDirectory}`,
        '--remote-debugging-port=9222',
      ],
    },
  },
  browsers: ['ChromeHeadlessCanimationCI'],
  browserNoActivityTimeout: 120000,
  captureTimeout: 120000,
});
