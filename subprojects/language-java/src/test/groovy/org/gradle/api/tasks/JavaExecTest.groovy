/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.api.tasks

import org.gradle.api.InvalidUserDataException
import org.gradle.test.fixtures.AbstractProjectBuilderSpec
import org.gradle.util.TestUtil

class JavaExecTest extends AbstractProjectBuilderSpec {

    def 'fails if custom executable does not exist'() {
        def task = project.tasks.create("run", JavaExec)
        def invalidExecutable = "invalid"

        when:
        task.executable = invalidExecutable
        execute(task)

        then:
        def e = thrown(TaskExecutionException)
        def cause = TestUtil.getRootCause(e) as InvalidUserDataException
        cause.message.contains("The configured executable does not exist")
        cause.message.contains(invalidExecutable)
    }
}
