pipeline {
    agent any
    tools {
        maven 'MAVEN_HOME'
    }
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/seeiif/selenium-Framework-Project.git'
            }
        }
        stage('Build') {
            steps {
                script {
                    if (isUnix()) {
                        // Run Maven on a Unix agent.
                        sh "'${tool 'MAVEN_HOME'}/bin/mvn' test -Pregression"
                    } else {
                        // Run Maven on a Windows agent.
                        bat(/"${tool 'MAVEN_HOME'}\bin\mvn" test -Pregression/)
                    }
                }
            }
        }
        stage('Results') {
            steps {
                junit '**/target/surefire-reports/TEST-*.xml'
                archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true
            }
        }
    }
}
