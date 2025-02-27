//
// MIT License
//
// Copyright (c) 2021 Vaishnav Anil
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.
//

package io.github.slimjar.plugin.configuration

import io.github.slimjar.plugin.SLIM_CONFIG_NAME
import io.github.slimjar.plugin.applyPlugins
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ConfigurationTest {

    private val project = ProjectBuilder.builder().build().also { it.applyPlugins() }

    @Test
    fun `Test slim configuration exists`() {
        val config = project.configurations.findByName(SLIM_CONFIG_NAME)
        assertThat(config).isNotNull
    }

    /*@Test
    fun `Test slimApi configuration exists`() {
        val config = project.configurations.findByName(SIM_API_CONFIG_NAME)
        assertThat(config).isNotNull
    }*/

    @Test
    fun `Test add slim dependency`() {
        assertThatCode {
            project.dependencies.add(SLIM_CONFIG_NAME, "com.google.code.gson:gson:2.8.6")
        }.doesNotThrowAnyException()
    }

    /*@Test
    fun `Test add slimApi dependency`() {
        assertThatCode {
            project.dependencies.add(SIM_API_CONFIG_NAME, "com.google.code.gson:gson:2.8.6")
        }.doesNotThrowAnyException()
    }*/
}
