/*
 *
 *  Copyright 2015-2019 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */

package springfox.petstore.integration.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;

public class MapBackedRepository<K, V extends Identifiable<K>> {
  Map<K, V> service = new HashMap<>();

  public void delete(K key) {
    service.remove(key);
  }

  public boolean exists(K key) {
    return service.containsKey(key);
  }

  public void add(V model) {
    service.put(model.getIdentifier(), model);
  }

  public V get(K key) {
    return service.get(key);
  }

  public V first() {
    return service.values().stream().findFirst().orElse(null);
  }
  
  public List<V> where(Predicate<V> criteria) {
    return
            service.values().stream()
            .filter(criteria).collect(toList());
  }
}
