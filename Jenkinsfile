pipeline {
    agent any
    parameters {
            string(name: 'VERSION', defaultValue: '1.0.0', description: 'The version of the artifact')
        }
    stages {
        stage('Cleaning and Compiling'){
            steps{
            sh 'mvn clean compile'
            }
        }
        stage('Generating package') {
            steps {
                sh 'mvn package'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
        stage('SonarQube analysis') {
            steps {
                sh 'mvn sonar:sonar \
                    -Dsonar.projectKey=Firas_Fejjeri \
                    -Dsonar.host.url=http://192.168.50.4:9000 \
                    -Dsonar.login=5ce3852b5c3fe5a0ff9f674d4cfc68c8e472fe93 '
            }
        }
        /*stage('Deploy to Nexus') {
                    steps {
                        script {
                            nexusArtifactUploader(
                                nexusVersion: 'nexus3',
                                protocol: 'http',
                                nexusUrl: '192.168.50.4:8081',
                                groupId: 'com.devops',
                                version: "${params.VERSION}",
                                repository: 'releases',
                                credentialsId: 'nexus',
                                artifacts: [
                                    [artifactId: 'devops-artifact',
                                    classifier: '',
                                    file: 'target/timesheet-devops-1.0.jar',
                                    type: 'jar']
                                ]
                            )
                        }
                    }
                }*/
        }
    }