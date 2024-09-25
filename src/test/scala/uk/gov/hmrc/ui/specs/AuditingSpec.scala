/*
 * Copyright 2024 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// /*
//  * Copyright 2024 HM Revenue & Customs
//  *
//  * Licensed under the Apache License, Version 2.0 (the "License");
//  * you may not use this file except in compliance with the License.
//  * You may obtain a copy of the License at
//  *
//  *     http://www.apache.org/licenses/LICENSE-2.0
//  *
//  * Unless required by applicable law or agreed to in writing, software
//  * distributed under the License is distributed on an "AS IS" BASIS,
//  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  * See the License for the specific language governing permissions and
//  * limitations under the License.
//  */

// package uk.gov.hmrc.ui.specs

// import uk.gov.hmrc.ui.pages.{CookieSettingsPage, ServiceTestPage}
// import uk.gov.hmrc.ui.pages.ServiceTestPage._
// import uk.gov.hmrc.ui.pages.CookieSettingsPage.{doNotUseMeasurementCookiesLabel, doNotUseSettingsCookiesLabel, submitButton, useMeasurementCookiesLabel, useSettingsCookiesLabel}
// import uk.gov.hmrc.ui.specs.tags.Local
// import com.github.tomakehurst.wiremock.client.WireMock.{anyRequestedFor, anyUrl}
// import play.api.Application
// import play.api.inject.guice.GuiceApplicationBuilder
// import uk.gov.hmrc.ui.WireMockEndpoints

// import uk.gov.hmrc.selenium.webdriver.Driver

// class AuditingSpec extends BaseAcceptanceSpec with WireMockEndpoints {
//   implicit override lazy val app: Application = new GuiceApplicationBuilder()
//     .configure(
//       Map(
//         "metrics.enabled"                 -> false,
//         "auditing.enabled"                -> true,
//         "auditing.consumer.baseUri.port"  -> endpointPort,
//         "play.http.router"                -> "testOnlyDoNotUseInAppConf.Routes",
//         "tracking-consent-frontend.port"  -> port,
//         "tracking-consent-frontend.url"   -> "/tracking-consent/tracking.js",
//         "tracking-consent-frontend.b-url" -> "/tracking-consent/tracking-b.js"
//       )
//     )
//     .build()

//   Feature("Auditing") {
//     Scenario("Accepting all cookies on the cookie settings page sends an auditing event", Local) {
//       Given("the user clears their cookies")
//       driver().manage().deleteAllCookies()

//       And("the user visits the cookie settings page")
//       CookieSettingsPage.goTo()

//       When("the user says yes to all cookies")
//       useMeasurementCookiesLabel.click()
//       useSettingsCookiesLabel.click()

//       And("clicks submit")
//       endpointServer.resetRequests()
//       submitButton.click()

//       Then("an audit event is sent")
//       eventually {
//         val requests = endpointServer.findAll(anyRequestedFor(anyUrl()))
//         requests.size                   should be(1)
//         requests.get(0).getBodyAsString should include(
//           "{\\\"measurement\\\":true,\\\"settings\\\":true}"
//         )
//       }
//     }

//     Scenario("The user refusing consent on the cookie settings page sends an auditing event", Local) {
//       Given("the user clears their cookies")
//       driver().manage().deleteAllCookies()

//       And("the user visits the cookie settings page")
//       CookieSettingsPage.goTo()

//       When("the user chooses no for all categories of cookie")
//       doNotUseMeasurementCookiesLabel.click()
//       doNotUseSettingsCookiesLabel.click()

//       And("clicks submit")
//       endpointServer.resetRequests()
//       submitButton.click()

//       Then("an audit event is sent")
//       eventually {
//         val requests = endpointServer.findAll(anyRequestedFor(anyUrl()))
//         requests.size                   should be(1)
//         requests.get(0).getBodyAsString should include(
//           "{\\\"measurement\\\":false,\\\"settings\\\":false}"
//         )
//       }
//     }

//     Scenario("Accepting all cookies on the cookie banner sends an audit event", Local) {
//       Given("the user clears their cookies")
//       driver().manage().deleteAllCookies()

//       When("the user visits the service test page")
//       ServiceTestPage.goTo()

//       When("the user clicks 'Accept all cookies'")
//       endpointServer.resetRequests()
//       acceptAdditionalCookiesButton.click()

//       Then("an audit event is sent")
//       eventually {
//         val requests = endpointServer.findAll(anyRequestedFor(anyUrl()))
//         requests.size                   should be(1)
//         requests.get(0).getBodyAsString should include(
//           "{\\\"measurement\\\":true,\\\"settings\\\":true}"
//         )
//       }
//     }
//   }
// }
