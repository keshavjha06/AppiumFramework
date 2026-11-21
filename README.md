# AppiumFramework
Appium mobile test automation framework with Page Object Model design using Java + Maven + TestNG.
Framework follows many of the industry best practices and supports Android and iOS in a single code base.

Technologies/Tools used in building the framework
=================================================
- IntelliJ IDEA / VS Code - IDE
- Appium (java-client 11) - Mobile Automation library
- Maven - Build automation tool
- Java 21 - Programming language
- TestNG - Test Management library
- Allure Report - Reporting framework
- JSON - Test Data
- XML - Static text
- GitHub - Version control
- Jenkins - CI/CD

Framework implements below best practices
=========================================
- Code reusability
- Code readability
- Scalable automation (demonstrated using multiple test classes)
- Uses explicit waits
- Abstraction layer for UI commands like click, sendkeys, etc.
- Parameterization using TestNG XML
- Alternate Design approach [Without using inheritance]
- Exception handling [using Try/Catch and TestNG Listener]
- Abstraction layer for test data
- Abstraction layer for static text
- Supports iOS and Android
- Demonstrates how to define UI elements that are common across pages (e.g. menu bar, side bar, etc.)
- How to recover from test failure/ how to write fail safe test cases
- Scrolling for both Android and iOS (using UiScrollable, mobile: scrollGesture, mobile: scroll)
- Demonstrates how to effectively capture Screenshots
- Integrated with Allure Report (page-object actions logged as @Step, screenshot attached on failure)

Prerequisites
=============
- Java 21
- Maven 3.9+
- Appium 3 installed globally via npm (`npm install -g appium`), with drivers:
  - `appium driver install uiautomator2`
  - `appium driver install xcuitest`
- Android: an emulator or real device visible in `adb devices`
- iOS: Xcode with an iOS simulator (tests are configured for **iPhone 18 Pro, iOS 27.2** in `IOSBaseTest`)

The Appium server is started automatically by the tests. It is located through the global npm root;
if Appium is installed somewhere else, pass its entry point with `-DappiumJS=/path/to/appium/index.js`.

Test apps
=========
Located in `src/test/java/org/academy/resources/`:
- `General-Store.apk` - Android tests
- `UIKitCatalog.app` - iOS tests (simulator build)

Configuration
=============
`src/main/java/org/academy/resources/data.properties`:

| Property            | Description                                   |
|---------------------|-----------------------------------------------|
| `ipAddress`         | Appium server IP (override with `-DipAddress`) |
| `port`              | Appium server port                            |
| `AndroidDeviceName` | Android device name capability                |

Running tests
=============
```bash
mvn test -PRegression          # Android: end-to-end + error validation (testNGSuites/testng.xml)
mvn test -PSmoke               # Android: Smoke group only (testNGSuites/testng_Smoke.xml)
mvn test -Dtest=IOSBasics      # iOS: UIKitCatalog
```

Reports
=======
Tests write Allure results to `target/allure-results/`. Page-object methods annotated with `@Step`
appear as report steps, and a screenshot is attached when a test fails (`TestUtils/Listeners`).

```bash
mvn allure:serve               # build the report and open it in a browser
mvn allure:report              # build static report -> target/site/allure-maven-plugin/index.html
allure serve target/allure-results   # same, using a locally installed Allure CLI
```

Note: `mvn clean` deletes previous results, so generate the report before cleaning.
TestNG/Surefire reports are still written to `target/surefire-reports/`.

Notes
=====
- `maven-surefire-plugin` is pinned to the **3.5.x** line. Version 3.6.0+ runs TestNG on the JUnit
  Platform and no longer supports TestNG suite XML files (`suiteXmlFiles`), which the Regression and
  Smoke profiles rely on.
- Surefire runs with the AspectJ weaver as a `-javaagent` (configured in `pom.xml`); this is what
  makes Allure `@Step` annotations work.
