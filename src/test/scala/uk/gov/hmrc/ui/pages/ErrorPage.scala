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

package uk.gov.hmrc.ui.pages

import org.openqa.selenium.{By, WebElement}
import uk.gov.hmrc.configuration.TestEnvironment

object ErrorPage extends BasePage {
  val url: String                     = TestEnvironment.url("tracking-consent-frontend") + "/test-only/non-existent-page"
  val title: String                   = "This page can’t be found"
  val acceptAdditionalCookies: String = "Accept additional cookies"
  val setCookiePreferences: String    = "View cookie preferences"

  def acceptAdditionalCookiesButton(): WebElement = findBy(buttonByPartialText(acceptAdditionalCookies))
  def gtmScript(): WebElement                     = findBy(By.cssSelector(s"""script[src*="gtm.js?id=GTM-NDJKHWK"]"""))
}
