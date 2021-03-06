package io.udash.rest.server

import io.udash.rest.{DefaultRESTFramework, RESTConverters}

class DefaultExposesREST[ServerRPCType : DefaultRESTFramework.ValidServerREST : DefaultRESTFramework.RPCMetadata](localRest: ServerRPCType)(
  implicit protected val localRpcAsRaw: DefaultRESTFramework.AsRawRPC[ServerRPCType]
) extends ExposesREST[ServerRPCType](localRest) with RESTConverters {
  override val framework = DefaultRESTFramework

  protected val rpcMetadata: DefaultRESTFramework.RPCMetadata[ServerRPCType] =
    implicitly[DefaultRESTFramework.RPCMetadata[ServerRPCType]]
}
