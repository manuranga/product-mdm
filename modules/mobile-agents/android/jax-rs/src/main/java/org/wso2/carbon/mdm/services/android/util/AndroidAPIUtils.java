/*
 * Copyright (c) 2015, WSO2 Inc. (http:www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.mdm.services.android.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.device.mgt.common.*;
import org.wso2.carbon.device.mgt.core.license.mgt.LicenseManagementService;
import org.wso2.carbon.device.mgt.core.service.DeviceManagementService;
import org.wso2.carbon.utils.multitenancy.MultitenantConstants;

/**
 * AndroidAPIUtil class provides utility functions used by Android REST-API classes.
 */
public class AndroidAPIUtils {

	private static Log log = LogFactory.getLog(AndroidAPIUtils.class);

	public static DeviceIdentifier convertToDeviceIdentifierObject(String deviceId) {
		DeviceIdentifier identifier = new DeviceIdentifier();
		identifier.setId(deviceId);
		identifier.setType(DeviceManagementConstants.MobileDeviceTypes.MOBILE_DEVICE_TYPE_ANDROID);
		return identifier;
	}

	public static DeviceManagementService getDeviceManagementService() {

		//TODO: complete login change super tenent context
		DeviceManagementService dmService;
		PrivilegedCarbonContext.startTenantFlow();
		PrivilegedCarbonContext ctx = PrivilegedCarbonContext.getThreadLocalCarbonContext();
		ctx.setTenantDomain(MultitenantConstants.SUPER_TENANT_DOMAIN_NAME);
		ctx.setTenantId(MultitenantConstants.SUPER_TENANT_ID);
		dmService =
				(DeviceManagementService) ctx.getOSGiService(DeviceManagementService.class, null);
		PrivilegedCarbonContext.endTenantFlow();
		return dmService;
	}

	public static LicenseManagementService getLicenseManagerService() {

		//TODO: complete login change super tenent context
		LicenseManagementService licenseManagementService;
		PrivilegedCarbonContext.startTenantFlow();
		PrivilegedCarbonContext ctx = PrivilegedCarbonContext.getThreadLocalCarbonContext();
		ctx.setTenantDomain(MultitenantConstants.SUPER_TENANT_DOMAIN_NAME);
		ctx.setTenantId(MultitenantConstants.SUPER_TENANT_ID);
		licenseManagementService =
				(LicenseManagementService) ctx.getOSGiService(LicenseManagementService.class, null);
		PrivilegedCarbonContext.endTenantFlow();
		return licenseManagementService;
	}

}
