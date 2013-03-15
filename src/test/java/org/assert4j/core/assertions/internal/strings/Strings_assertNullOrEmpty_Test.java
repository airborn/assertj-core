/*
 * Created on Dec 26, 2010
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 * 
 * Copyright @2010-2011 the original author or authors.
 */
package org.assert4j.core.assertions.internal.strings;

import static org.assert4j.core.assertions.error.ShouldBeNullOrEmpty.shouldBeNullOrEmpty;
import static org.assert4j.core.assertions.test.TestData.someInfo;
import static org.assert4j.core.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;


import static org.mockito.Mockito.verify;

import org.assert4j.core.assertions.core.AssertionInfo;
import org.assert4j.core.assertions.internal.Strings;
import org.assert4j.core.assertions.internal.StringsBaseTest;
import org.junit.Test;


/**
 * Tests for <code>{@link Strings#assertNullOrEmpty(AssertionInfo, String)}</code>.
 * 
 * @author Alex Ruiz
 * @author Joel Costigliola
 */
public class Strings_assertNullOrEmpty_Test extends StringsBaseTest {

  @Test
  public void should_fail_if_actual_is_not_null_and_is_not_empty() {
    AssertionInfo info = someInfo();
    try {
      strings.assertNullOrEmpty(info, "Yoda");
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldBeNullOrEmpty("Yoda"));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }

  @Test
  public void should_pass_if_actual_is_null() {
    strings.assertNullOrEmpty(someInfo(), null);
  }

  @Test
  public void should_pass_if_actual_is_empty() {
    strings.assertNullOrEmpty(someInfo(), "");
  }

  @Test
  public void should_fail_if_actual_is_not_null_and_is_not_empty_whatever_custom_comparison_strategy_is() {
    AssertionInfo info = someInfo();
    try {
      stringsWithCaseInsensitiveComparisonStrategy.assertNullOrEmpty(info, "Yoda");
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldBeNullOrEmpty("Yoda"));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }

  @Test
  public void should_pass_if_actual_is_null_whatever_custom_comparison_strategy_is() {
    stringsWithCaseInsensitiveComparisonStrategy.assertNullOrEmpty(someInfo(), null);
  }

  @Test
  public void should_pass_if_actual_is_empty_whatever_custom_comparison_strategy_is() {
    stringsWithCaseInsensitiveComparisonStrategy.assertNullOrEmpty(someInfo(), "");
  }
}