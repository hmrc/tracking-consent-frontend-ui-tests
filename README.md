# tracking-consent-frontend-ui-tests

Tracking Consent Frontend UI tests.

## Pre-requisites

### Services

Start Mongo Docker container as follows:

```bash
docker run --rm -d -p 27017:27017 --name mongo percona/percona-server-mongodb:5.0
```

Start `TRACKING_CONSENT_FRONTEND` services as follows:

```bash
sm2 --start TRACKING_CONSENT_FRONTEND
```

## Tests

## Scalafmt

Check all project files are formatted as expected as follows:

```bash
sbt scalafmtCheckAll scalafmtCheck
```

Format `*.sbt` and `project/*.scala` files as follows:

```bash
sbt scalafmtSbt
```

Format all project files as follows:

```bash
sbt scalafmtAll
```

## Tests

Run tests as follows:

* Argument `<prepare>` can be `clean` or `scalafmtAll`
* Argument `<browser>` must be `chrome`, `edge`, or `firefox`.
* Argument `<environment>` must be `local`, `dev`, `qa` or `staging`.

```bash
sbt `<prepare>` -Dbrowser="<browser>" -Denvironment="<environment>" "testOnly uk.gov.hmrc.ui.specs.*" testReport
```

Example:

```bash
sbt scalafmtAll -Dbrowser="chrome" -Denvironment="local" "testOnly uk.gov.hmrc.ui.specs.*" testReport
```

## License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
