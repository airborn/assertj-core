/*
 * Created on Mar 5, 2012
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
 * Copyright @2012 the original author or authors.
 */
package org.assert4j.core.assertions.internal.iterables;

import static org.assert4j.core.assertions.error.ElementsShouldNotHave.elementsShouldNotHave;
import static org.assert4j.core.assertions.test.TestData.someInfo;
import static org.assert4j.core.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.assert4j.core.util.Lists.newArrayList;


import static org.mockito.Mockito.verify;

import org.assert4j.core.assertions.core.AssertionInfo;
import org.assert4j.core.assertions.internal.Iterables;
import org.assert4j.core.assertions.internal.IterablesWithConditionsBaseTest;
import org.junit.Test;


/**
 * Tests for <code>{@link Iterables#assertDoNotHave(AssertionInfo, Iterable, org.assert4j.core.assertions.core.Condition)}</code> .
 * 
 * @author Nicolas François
 * @author Mikhail Mazursky
 * @author Joel Costigliola
 */
public class Iterables_assertDoNotHave_Test extends IterablesWithConditionsBaseTest {

  @Test
  public void should_pass_if_each_element_not_satisfies_condition() {
    actual = newArrayList("Solo", "Leia");
    iterables.assertDoNotHave(someInfo(), actual, jediPower);
    verify(conditions).assertIsNotNull(jediPower);
  }

  @Test
  public void should_throw_error_if_condition_is_null() {
    thrown.expectNullPointerException("The condition to evaluate should not be null");
    actual = newArrayList("Solo", "Leia");
    iterables.assertDoNotHave(someInfo(), actual, null);
    verify(conditions).assertIsNotNull(null);
  }

  @Test
  public void should_fail_if_condition_is_met() {
    testCondition.shouldMatch(false);
    AssertionInfo info = someInfo();
    try {
      actual = newArrayList("Solo", "Leia", "Yoda");
      iterables.assertDoNotHave(someInfo(), actual, jediPower);
    } catch (AssertionError e) {
      verify(conditions).assertIsNotNull(jediPower);
      verify(failures).failure(info, elementsShouldNotHave(actual, newArrayList("Yoda"), jediPower));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }

}