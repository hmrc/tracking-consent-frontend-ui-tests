/*
 * Copyright 2026 HM Revenue & Customs
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

package uk.gov.hmrc.ui.utils

import org.openqa.selenium.devtools.NetworkInterceptor
import org.openqa.selenium.remote.Augmenter
import org.openqa.selenium.remote.http.{Filter, HttpHandler, HttpRequest}
import org.scalatest.{Outcome, TestSuite, TestSuiteMixin}
import uk.gov.hmrc.selenium.webdriver.Driver

import java.nio.charset.Charset

trait RequestCaptureFilter extends TestSuiteMixin { this: TestSuite =>
  var interceptedRequests: Seq[HttpRequest] = Seq.empty

  def deleteInterceptedRequests(): Unit = interceptedRequests = Seq.empty

  def interceptedRequestsContainUrlAndContent(url: String, content: String): Boolean =
    interceptedRequests
      .find(_.getUri.endsWith(url))
      .exists(_.getContent.contentAsString(Charset.defaultCharset()) == content)

  abstract override def withFixture(test: NoArgTest): Outcome = {
    // Add a network interceptor with a filter that adds all requests to the sequence above
    new NetworkInterceptor(
      new Augmenter().augment(Driver.instance),
      new Filter {
        override def apply(next: HttpHandler): HttpHandler = (request: HttpRequest) => {
          interceptedRequests = interceptedRequests :+ request
          next.execute(request)
        }
      }
    )

    super.withFixture(test)
  }
}
