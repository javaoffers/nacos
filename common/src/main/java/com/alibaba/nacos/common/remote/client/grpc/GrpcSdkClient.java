/*
 * Copyright 1999-2020 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.nacos.common.remote.client.grpc;

/**
 * gRPC client for sdk.
 *
 * @author liuzunfei
 * @version $Id: GrpcSdkClient.java, v 0.1 2020年09月07日 11:05 AM liuzunfei Exp $
 */
public class GrpcSdkClient extends GrpcClient {
    
    private static final String NACOS_SERVER_GRPC_PORT_DEFAULT_OFFSET = "1000";
    
    /**
     * Empty constructor.
     *
     * @param name name of client.
     */
    public GrpcSdkClient(String name) {
        super(name);
    }
    
    @Override
    public int rpcPortOffset() {
        return Integer.parseInt(System.getProperty(
                NACOS_SERVER_GRPC_PORT_OFFSET_KEY, NACOS_SERVER_GRPC_PORT_DEFAULT_OFFSET));
    }

    @Override
    public int rpcPortOffset(ServerInfo serverInfo) {
        int grpcClusterPort = Integer.parseInt(System.getProperty(GRPC_SERVER_SDK_PORT, GRPC_SERVER_DEFAULT_USELESS_PORT));
        if(grpcClusterPort == -1){
            grpcClusterPort = rpcPortOffset();
        }else {
            grpcClusterPort = grpcClusterPort - serverInfo.getServerPort();
        }
        return grpcClusterPort;
    }

}