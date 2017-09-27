/*
 * Copyright 2017 Agilx, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ai.apptuit.metrics.jinsight.modules.jvm;

import static java.lang.management.ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME;
import static java.lang.management.ManagementFactory.getPlatformMBeanServer;
import static java.lang.management.ManagementFactory.newPlatformMXBeanProxy;

import com.codahale.metrics.Gauge;
import java.io.IOException;
import javax.management.MBeanServerConnection;

/**
 * @author Rajiv Shivane
 */
class ProcessCpuTicksGauge implements Gauge<Long> {

  private final com.sun.management.OperatingSystemMXBean osMxBean;

  public ProcessCpuTicksGauge() throws ClassNotFoundException, IOException {

    MBeanServerConnection mbsc = getPlatformMBeanServer();
    Class.forName("com.sun.management.OperatingSystemMXBean");

    osMxBean = newPlatformMXBeanProxy(mbsc, OPERATING_SYSTEM_MXBEAN_NAME,
        com.sun.management.OperatingSystemMXBean.class);

  }

  @Override
  public Long getValue() {
    return osMxBean.getProcessCpuTime();
  }
}