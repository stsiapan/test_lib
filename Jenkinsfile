@Library('test-lib') _

pipeline {
    agent any
    parameters {
        string(name: 'version', defaultValue: '', description: 'select the version of an artifact')
        choice(name: 'envir', choices: ['labs', 'prod'], description: 'pick environment')
        string(name: 's3bucket', defaultValue: 'my-jenkins-s3-wedwedlkwleknd', description: 'set the s3bucket name')
    }
    stages {
        stage('CHECK for existing') {
            steps {
                script{
                    checking.checkArtifacts(params.envir, params.version, params.s3bucket)
                }
                //only for testing
                sh  """
                    echo "${env.FileExists}"
                    echo version is ${version}
                    aws --version
                    """
            }
        }

        stage('building') {
            when { 
                environment name: 'FileExists', value: 'false'
            }
            steps {
                //only for testing
                echo "${env.FileExists}"
                echo "building is started"
                sh "touch {1..4}-${params.version}.zip"
                sh "touch orion-content-${params.version}.jar"
                sh "echo hello_from_version_${params.version} > 123-${params.version}.txt"
            }
        }

       stage('copy to S3 bucket') {
           when {
               environment name: 'FileExists', value: 'false'
           }
           steps {
               script {
                   aws.copyToBucket(params.envir, params.version, params.s3bucket)
               }
           }
       }

       stage('deploy') {
           steps {
               script {
                   aws.copyFromBucket(params.envir, params.version, params.s3bucket)
               }
               echo "deploy is starting" 
               echo "On this step we copy artifacts from localhost to remote instance by curl"
               sh "ls -la"
           }
       }
    }
}
