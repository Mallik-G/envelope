/**
 * Licensed to Cloudera, Inc. under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. Cloudera, Inc. licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cloudera.labs.envelope.input.translate;

import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.StructType;

import com.typesafe.config.Config;

/**
 * Translators turn raw objects into structured Spark SQL Rows.
 * @param <K> The data type of the input keys
 * @param <V> The data type of the input values
 */
public interface Translator<K, V> {

  /**
   * Configure the translator.
   * This is called once by Envelope, immediately after translator instantiation.
   * @param config The configuration of the translator.
   */
  void configure(Config config);

  /**
   * Translate the raw key and value into one or more structured rows.
   * @param key The key of the value, which could be null if not provided or is not applicable.
   * @param value
   * @return An iterable collection of Spark SQL Rows for the keyed value. If the keyed value
   * only translates to a single Spark SQL Row then it can be wrapped with Collection#singleton.
   * @throws Exception
   */
  Iterable<Row> translate(K key, V value) throws Exception;

  /**
   * Get the schema of the translated objects.
   * @return The Spark SQL schema for the Rows that the translator will generate.
   */
  StructType getSchema();

}
