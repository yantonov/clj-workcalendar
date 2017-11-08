# clj-workcalendar

A Clojure/ClojureScript tiny library designed to simplify manupilations with dates (in the sense of [working calendar](http://www.superjob.ru/proizvodstvennyj_kalendar/)) from javascript code.

## Usage

Grab work calendar data (and generate clojurescript data file with work calendar).
```bash
bin/build-data-files.sh
```
Generate javascript source from clojurescript sources.
```bash
bin/generate-js.sh
```

## CI
Run unit tests (check collect work calendar data from external sites)
```bash
bin/test.sh

```

Run functional tests (check javascript api using [phantomjs](http://phantomjs.org/download.html)
prerequisites: [phantomjs](http://phantomjs.org/download.html) (checked at 2.1.1 version)
```bash
bin/cljs-test.sh
```

## License

Copyright © 2017 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
