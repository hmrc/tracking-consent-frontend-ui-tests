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

package uk.gov.hmrc.ui.specs

import uk.gov.hmrc.ui.pages.LegacyServiceTestPage
import uk.gov.hmrc.ui.pages.LegacyServiceTestPage._
import org.scalatest.tagobjects.Retryable

class LegacyTestPageSpec extends BaseAcceptanceSpec {
  Feature("Legacy Service Test page") {
    Scenario("No Javascript errors occur", Retryable) {
      Given("the user clears their cookies")
      driver().manage().deleteAllCookies()

      When("the user visits the legacy service test page")
      LegacyServiceTestPage.goTo()

      Then("the banner should be displayed with the title 'Cookies on HMRC services'")
      eventually {
        LegacyServiceTestPage.h2Text() shouldBe "Cookies on HMRC services"
      }

      And("no Javascript console errors are thrown")
      // TODO
      // consoleErrors should equal(Seq.empty)
    }
  }
}
