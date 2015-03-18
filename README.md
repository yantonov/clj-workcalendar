# cljs-workcalendar

A Clojure tiny library designed to manipulate with work calendar from javascriptcode.

## Usage

Grab work calendar data (and generate clojurescript file with work calendar).
```bash
bin/grab-data.sh
```
Generate javascript source from clojurescript sources.
```bash
bin/generate-js.sh
```

## CI
Run unit tests (check collect work calendar data)
```bash
bin/unit-test.sh
```

Run functional tests (check javascript api using [phantomjs](http://phantomjs.org/download.html)
prerequisites: [phantomjs](http://phantomjs.org/download.html) (checked at 1.9.8 version)
```bash
bin/functional-test.sh
```

## License

Copyright © 2015 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
