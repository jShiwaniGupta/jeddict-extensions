/**
 * Copyright [2016] Gaurav Gupta
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.netbeans.jcode.servlet.util;

import java.io.IOException;
import org.netbeans.api.project.Project;
import org.netbeans.modules.j2ee.common.dd.DDHelper;
import org.netbeans.modules.j2ee.dd.api.web.DDProvider;
import org.netbeans.modules.j2ee.dd.api.web.WebApp;
import org.netbeans.modules.j2ee.dd.api.web.WelcomeFileList;
import org.netbeans.modules.web.api.webmodule.WebModule;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

/**
 *
 * @author Gaurav Gupta
 */
public class ServletUtil {
    public static void setWelcomeFiles(Project project, String text) throws IOException {
        FileObject webXml = null;
        // Create web.xml
        WebModule wm1 = WebModule.getWebModule(project.getProjectDirectory());
        if (wm1 != null && wm1.getDocumentBase() != null) {
            FileObject webInf = wm1.getWebInf();
            if (webInf == null) {
                webInf = FileUtil.createFolder(wm1.getDocumentBase(), "WEB-INF");    //NOI18N
            }
            webXml = wm1.getDeploymentDescriptor();
            if (webXml == null) {
                webXml = DDHelper.createWebXml(wm1.getJ2eeProfile(), webInf);
            }
        }

        WebApp webApp = null;
        if (webXml != null) {
            webApp = DDProvider.getDefault().getDDRoot(webXml);

            if (text.length() == 0) {
                webApp.setWelcomeFileList(null);
            } else {
                java.util.List<String> wfList = new java.util.ArrayList<>();
                java.util.StringTokenizer tok = new java.util.StringTokenizer(text, ",");
                while (tok.hasMoreTokens()) {
                    String wf = tok.nextToken().trim();
                    if (wf.length() > 0 && !wfList.contains(wf)) {
                        wfList.add(wf);
                    }
                }
                
                WelcomeFileList welcomeFileList = webApp.getSingleWelcomeFileList();
                if (welcomeFileList == null) {
                    try {
                        welcomeFileList = (WelcomeFileList) webApp.createBean("WelcomeFileList"); //NOI18N
                        webApp.setWelcomeFileList(welcomeFileList);
                    } catch (ClassNotFoundException ex) {
                    }
                }
//                if (wfList.size() == 1) {
//                    welcomeFileList.addWelcomeFile(wfList.get(0));
//                } else {
                    String[] welcomeFiles = new String[wfList.size()];
                    wfList.toArray(welcomeFiles);
                    welcomeFileList.setWelcomeFile(welcomeFiles);
//                }
            }
            
            webApp.write(webXml);
        }
    }

}
