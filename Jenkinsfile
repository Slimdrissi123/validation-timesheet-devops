pipeline {
    agent any
    environment {
        MAIN_VERSION = "1.1"
        BUILD_VERSION = "${MAIN_VERSION}-b${env.BUILD_NUMBER}"
        DOCKER_CREDENTIALS = credentials('307f196d-c538-49e8-b350-bc5caa31b442')
        EMAIL_CREDENTIALS_ID = credentials('gmailcredentials')
    }
    stages {
        stage('Compile') {
            steps {
                sh 'mvn compile'
            }
        }
        stage('Test Junit & Mockito') {
            steps {
                sh 'mvn test'
            }
        }
        stage('runBuild') {
                    steps {
                        sh "mvn install -Dproduct.build.number=b${env.BUILD_NUMBER}"
                    }
                }

        stage('Jacoco report'){
            steps{
                sh 'mvn jacoco:report'
            }
        }
        stage('SonarQube analysis') {
            steps {
                sh '''
                mvn sonar:sonar \
                    -Dsonar.projectKey=timesheet-devops \
                    -Dsonar.host.url=http://192.168.56.2:9000 \
                    -Dsonar.login=110b5a8288a0aee98ec5dcd225b0a3fc3b0f8441
                '''
            }
        }
        stage('Set Version') {
            steps {
                sh "mvn versions:set -DnewVersion=${BUILD_VERSION}"
                sh "mvn versions:commit"
            }
        }
        stage('Docker Build') {
            steps {
                script {
                    docker.build("ssdrissi/timesheet-devops:${BUILD_VERSION}")
                }
            }
        }
        stage('Push Docker Image to DockerHub') {
                    steps {
                        script {
                            withCredentials([usernamePassword(credentialsId: '307f196d-c538-49e8-b350-bc5caa31b442', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {

                            sh "docker login -u ${DOCKERHUB_USERNAME} -p ${DOCKERHUB_PASSWORD}"
                            sh "docker push ssdrissi/timesheet-devops:${BUILD_VERSION}"
                        }
                    }
                }
        }
        stage('Docker compose (FrontEnd BackEnd MySql)') {
            steps {
                script {
                    sh '/usr/local/bin/docker-compose down -v'
                    sh '/usr/local/bin/docker-compose up -d'
                }
            }
        }

        stage('Deploy to nexus') {
            steps {
                echo 'Deploying to Nexus server'
                sh "mvn deploy "
            }
        }
        stage('Generate documentation') {
                steps {
                    sh 'mvn javadoc:javadoc'
                }
                post {
                    always {
                        archiveArtifacts artifacts: '**/target/site/apidocs/**', allowEmptyArchive: true
                    }
                }
            }

        stage('JaCoCo coverage report') {
                    steps {
                        step([$class: 'JacocoPublisher',
                              execPattern: '**/target/jacoco.exec',
                              classPattern: '**/classes',
                              sourcePattern: '**/src',
                              exclusionPattern: '*/target/**/,**/*Test*,**/*_javassist/**'
                        ])
                    }
                }


        stage('Test Email') {
            steps {
                emailext (
                    subject: "Test Email from Jenkins Pipeline",
                    body: "Pipeline is AAAAAA OKAAYYYYYY :).",
                    to: "slim.drissi@esprit.tn"


                )
            }
        }

}
}
