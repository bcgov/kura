/*******************************************************************************
 * Copyright (c) 2016, 2017 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *  Eurotech
 *  Amit Kumar Mondal
 *  
 *******************************************************************************/
package org.eclipse.kura.web.client.ui.wires;

import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.constants.IconType;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class WireComponentsAnchorListItem extends AnchorListItem {

    private boolean isEmitter;
    private boolean isReceiver;

    public WireComponentsAnchorListItem(final String factoryPid, final boolean isEmitter, final boolean isReceiver) {
        super();
        this.isEmitter = isEmitter;
        this.isReceiver = isReceiver;
        super.setIcon(this.getFactoryIcon());
        super.setText(WiresPanelUi.getFormattedPid(factoryPid));

        super.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                if (factoryPid.contains(WiresPanelUi.WIRE_ASSET)) {
                    WiresPanelUi.driverInstanceForm.setVisible(true);
                } else {
                    WiresPanelUi.driverInstanceForm.setVisible(false);
                }
                WiresPanelUi.factoryPid.setValue(factoryPid);
                WiresPanelUi.assetModal.show();
                WireComponentsAnchorListItem.this.setActive(true);
            }
        });
    }

    private IconType getFactoryIcon() {
        if (this.isEmitter && this.isReceiver) {
            return IconType.EXCHANGE;
        }
        if (this.isEmitter) {
            return IconType.LONG_ARROW_LEFT;
        }
        return IconType.LONG_ARROW_RIGHT;
    }
}