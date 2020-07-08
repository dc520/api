pipeline {
    agent any
    stages {
    
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: "master"]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: "https://github.com/dc520/readme.git"]]])
            }
        }

        stage('Setup') {
            steps {
                script {
                    env.GIT_COMMIT = sh(script: "git rev-parse --short HEAD", returnStdout: true).trim()
                    env.GIT_COMMIT_SHORT = sh(script: "git log -1 --pretty=format:%h", returnStdout: true).trim()
                    env.VERSION = "master" + "-" +env.BUILD_NUMBER + "-" + env.GIT_COMMIT_SHORT

                }
            }
        }
    
        stage('build image') {
            steps {
                script {
                    //Get hrb credentials id.
                    //withCredentials([Skipdetection(credentialsId: 'hrb', xxVariable: 'xx', xxVariable: 'xx')]) {
                        
                        //docker login -u ${xx} -p {xx} hrb
                        //docker build -t xxx/xxx:tag .
                        //docker tag xxx/xxx hrb/xxx/xxx:tag
                        //docker push hrb/xxx/xxx:tag
                        echo "pass"
                    //}
                }
            }
        }
        
        stage("Set Build Description"){
            steps {
                script {
                    currentBuild.displayName = "${env.VERSION}"
                    currentBuild.description = "hrb/xxx/api:${env.VERSION}"
                }
            }
        }

    }
    post {
        success {
            echo 'I succeeded!'
        }
        unstable {
            echo 'I am unstable :/'
        }
        failure {
            echo 'I failed :('
        }
        changed {
            echo 'Things are different...'
        }
    }
}
