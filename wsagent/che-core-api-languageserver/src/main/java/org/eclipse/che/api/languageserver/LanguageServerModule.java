/*******************************************************************************
 * Copyright (c) 2012-2017 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package org.eclipse.che.api.languageserver;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import org.eclipse.che.api.languageserver.launcher.LanguageServerLauncher;
import org.eclipse.che.api.languageserver.messager.InitializeEventMessenger;
import org.eclipse.che.api.languageserver.messager.PublishDiagnosticsParamsJsonRpcTransmitter;
import org.eclipse.che.api.languageserver.messager.ShowMessageJsonRpcTransmitter;
import org.eclipse.che.api.languageserver.registry.LanguageServerRegistry;
import org.eclipse.che.api.languageserver.registry.LanguageServerRegistryImpl;
import org.eclipse.che.api.languageserver.registry.ServerInitializer;
import org.eclipse.che.api.languageserver.registry.ServerInitializerImpl;
import org.eclipse.che.api.languageserver.service.LanguageRegistryService;
import org.eclipse.che.api.languageserver.service.LanguageServerInitializationHandler;
import org.eclipse.che.api.languageserver.service.TextDocumentService;
import org.eclipse.che.api.languageserver.service.WorkspaceService;
import org.eclipse.che.api.languageserver.shared.model.LanguageDescription;

public class LanguageServerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(LanguageServerRegistry.class).to(LanguageServerRegistryImpl.class);
        bind(ServerInitializer.class).to(ServerInitializerImpl.class);
        bind(LanguageRegistryService.class);
        bind(WorkspaceService.class);
        bind(InitializeEventMessenger.class);
        Multibinder.newSetBinder(binder(), LanguageServerLauncher.class);

        bind(TextDocumentService.class).asEagerSingleton();
        bind(PublishDiagnosticsParamsJsonRpcTransmitter.class).asEagerSingleton();
        bind(ShowMessageJsonRpcTransmitter.class).asEagerSingleton();
        bind(LanguageServerInitializationHandler.class).asEagerSingleton();
        Multibinder.newSetBinder(binder(), LanguageDescription.class);
    }
}
