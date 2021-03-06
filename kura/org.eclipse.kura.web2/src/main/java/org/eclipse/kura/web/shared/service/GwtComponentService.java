/*******************************************************************************
 * Copyright (c) 2011, 2016 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.eclipse.kura.web.shared.service;

import java.util.List;
import java.util.Map;

import org.eclipse.kura.web.shared.GwtKuraException;
import org.eclipse.kura.web.shared.model.GwtConfigComponent;
import org.eclipse.kura.web.shared.model.GwtXSRFToken;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Set of methods that can be used to get or update component configurations. Those methods will work in relation with a
 * {@link org.eclipse.kura.configuration.ConfigurationService} implementation running in the framework.
 *
 */
@RemoteServiceRelativePath("component")
public interface GwtComponentService extends RemoteService {

    /**
     * Returns a the list of component configurations. This list is filtered from a set of services that need to be
     * hidden and not displayed in the "Services" section of the local web ui.
     * 
     * @param xsrfToken
     *            the cross site request forgery token.
     * @return a list of GwtConfigComponent that represent the GWT Component Configuration that will be rendered in the
     *         client part of the web ui.
     * @throws GwtKuraException
     *             if the XSRF verification fails.
     * @throws GwtKuraException
     *             if the Configuration Service cannot be located.
     * @throws GwtKuraException
     *             if the list of services that need to be hidden from rendering cannot be filled.
     * @throws GwtKuraException
     *             if the list of components registered in the framework cannot be extracted.
     */
    public List<GwtConfigComponent> findServicesConfigurations(GwtXSRFToken xsrfToken) throws GwtKuraException;

    /**
     * Returns a filtered list of component configurations. This list is named filtered because the configurations of
     * the components tracked are not complete but mapped to the respective component metatype. This means that eventual
     * additional properties of the component that are tracked by the ConfigurationAdmin will not be reflected in the
     * resulting GwtConfigComponent returned.
     * 
     * @param xsrfToken
     *            the cross site request forgery token.
     * @return a list of GwtConfigComponent.
     * @throws GwtKuraException
     *             if the XSRF verification fails.
     * @throws GwtKuraException
     *             if the Configuration Service cannot be located.
     * @throws GwtKuraException
     *             if the component configurations cannot be extracted.
     */
    public List<GwtConfigComponent> findFilteredComponentConfigurations(GwtXSRFToken xsrfToken) throws GwtKuraException;

    /**
     * Returns a list containing the component configuration of the requested component specified by the provided PID.
     * As for {@link #findFilteredComponentConfigurations(GwtXSRFToken)}, the returned configuration is filtered because
     * contains only the properties that can be mapped to the component metatype.
     * 
     * @param xsrfToken
     *            the cross site request forgery token.
     * @param pid
     *            the Kura persistent identifier, <i>kura.service.pid</i>, of the component whose configuration needs to
     *            be returned as result.
     * @return a list of GwtConfigComponent.
     * @throws GwtKuraException
     *             if the XSRF verification fails.
     * @throws GwtKuraException
     *             if the Configuration Service cannot be located.
     * @throws GwtKuraException
     *             if the component configuration for the specified component cannot be found.
     */
    public List<GwtConfigComponent> findFilteredComponentConfiguration(GwtXSRFToken xsrfToken, String pid)
            throws GwtKuraException;

    /**
     * This method returns the list of component configurations as extracted from the
     * {@link org.osgi.service.cm.ConfigurationAdmin}.
     * 
     * @param xsrfToken
     *            the cross site request forgery token.
     * @return a list of GwtConfigComponent.
     * @throws GwtKuraException
     *             if the XSRF verification fails.
     * @throws GwtKuraException
     *             if the Configuration Service cannot be located.
     * @throws GwtKuraException
     *             if the component configurations cannot be extracted.
     */
    public List<GwtConfigComponent> findComponentConfigurations(GwtXSRFToken xsrfToken) throws GwtKuraException;

    /**
     * This method returns a list containing the component configuration associated to the specified PID and tracked by
     * the {@link org.osgi.service.cm.ConfigurationAdmin}.
     * 
     * @param xsrfToken
     *            the cross site request forgery token.
     * @param pid
     *            the Kura persistent identifier, <i>kura.service.pid</i>.
     * @return a list of GwtConfigComponent.
     * @throws GwtKuraException
     *             if the XSRF verification fails.
     * @throws GwtKuraException
     *             if the Configuration Service cannot be located.
     * @throws GwtKuraException
     *             if the component configuration for the specified component cannot be found.
     */
    public List<GwtConfigComponent> findComponentConfiguration(GwtXSRFToken xsrfToken, String pid)
            throws GwtKuraException;

    /**
     * This method gets an updated component configuration in form of a {@link GwtConfigComponent} and applies those
     * changes using the {@link org.eclipse.kura.configuration.ConfigurationService}
     * 
     * @param xsrfToken
     *            the cross site request forgery token.
     * @param configComponent
     *            a GwtConfigComponent instance that contains the updated configuration.
     * @throws GwtKuraException
     *             if the XSRF verification fails.
     * @throws GwtKuraException
     *             if the Configuration Service cannot be located.
     * @throws GwtKuraException
     *             if the current component configuration cannot be extracted from the Configuration Service.
     * @throws GwtKuraException
     *             if the component configuration changes could not be applied.
     */
    public void updateComponentConfiguration(GwtXSRFToken xsrfToken, GwtConfigComponent configComponent)
            throws GwtKuraException;
            
    public void createFactoryComponent(GwtXSRFToken xsrfToken, String factoryPid, String pid) throws GwtKuraException;

    public void deleteFactoryConfiguration(GwtXSRFToken xsrfToken, String pid, boolean takeSnapshot)
            throws GwtKuraException;
    
    public List<String> findFactoryComponents(GwtXSRFToken xsrfToken) throws GwtKuraException;

    public GwtConfigComponent findWireComponentConfigurationFromPid(GwtXSRFToken xsrfToken, String pid,
            String factoryPid, Map<String, Object> extraProps) throws GwtKuraException;
}
