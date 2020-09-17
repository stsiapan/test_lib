def checkArtifacts(envir, version, s3bucket) {
    def ExistStatus = sh(returnStatus: true, 
                         script: "aws s3 ls s3://${s3bucket}/${envir}/ | grep ${version}"
                         ).toInteger()
    println("env status equals ${ExistStatus}")
    if (ExistStatus == 0)  {
        env.FileExists = true
        echo "status equals ${ExistStatus} ZERO"
    } else {
        env.FileExists = false
        echo "status equals ${ExistStatus} NOT ZERO"
    }
    return env.FileExists
}
