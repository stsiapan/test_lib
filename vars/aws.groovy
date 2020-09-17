def copyToBucket(envir, version, s3bucket) {
    try {
        def copyTo = sh(returnStdout: true,
                        script: "aws s3 cp ./ s3://${s3bucket}/${envir}/${version}/ --exclude '*' --include '*.jar' --include '*.zip' --include '*.txt' --recursive"
                        ).trim()
        println("CORRECT log message") 
    } catch (err) {
        println("ERROR log message")
        print(err)
    }
}

def copyFromBucket(envir, version, s3bucket) {
    try {
        def copyFrom = sh(returnStdout: true,
                        script: "aws s3 cp s3://${s3bucket}/${envir}/${version}/ ./ --recursive" 
                        ).trim()
        println(copyFrom)
        println("CORRECT log message")
    } catch (err) {
        println("ERROR log message")
        print(err)
    }
}
