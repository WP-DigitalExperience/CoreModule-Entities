/**
 * webTools-contentengine
 * Copyright (C) 2016  Thorsten Marx (kontakt@thorstenmarx.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.thorstenmarx.webtools.core.modules.configuration.module;


import com.thorstenmarx.modules.api.ModuleLifeCycleExtension;
import com.thorstenmarx.modules.api.annotation.Extension;
import com.thorstenmarx.webtools.api.CoreModuleContext;
import com.thorstenmarx.webtools.api.entities.Entities;
import com.thorstenmarx.webtools.core.modules.entities.EntitiesImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author marx
*/
@Extension(ModuleLifeCycleExtension.class)
public class CoreModuleEntitiesModuleLifeCycle extends ModuleLifeCycleExtension {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoreModuleEntitiesModuleLifeCycle.class);
	
	public static EntitiesImpl entities;

	
	@Override
	public void activate() {
		entities = new EntitiesImpl(configuration.getDataDir(), ((CoreModuleContext)getContext()).getCluster());
//		entities = new EntitiesImpl(((CoreModuleContext)getContext()).getDataPath());
        entities.open();
		
		getContext().serviceRegistry().register(Entities.class, entities);
	}

	@Override
	public void deactivate() {
		try {
			getContext().serviceRegistry().unregister(Entities.class, entities);
			entities.close();
		} catch (Exception ex) {
			LOGGER.error("", ex);
		}
	}

	@Override
	public void init() {

	}

	
	
	
	
}
