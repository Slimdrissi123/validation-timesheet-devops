pipeline {
    agent any
    environment {
        MAIN_VERSION = "1.1"
        BUILD_VERSION = "${MAIN_VERSION}-b${env.BUILD_NUMBER}"
        DOCKER_CREDENTIALS = credentials('307f196d-c538-49e8-b350-bc5caa31b442')
    }
    stages {
        stage('Clean') {
            steps {
                sh "mvn clean install -Dproduct.build.number=b${env.BUILD_NUMBER}"
            }
        }
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
                           sh '''

                            docker-compose up -d
                            '''
                        }
                    }
                }
        stage('Deploy to nexus') {
            steps {
                echo 'Deploying to Nexus server'
                sh 'mvn deploy'
            }
        }
    }
}
