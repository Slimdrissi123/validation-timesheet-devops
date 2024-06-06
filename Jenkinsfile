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
                    sh '/usr/local/bin/docker-compose up -d'
                }
            }
        }

        stage('Deploy to nexus') {
            steps {
                echo 'Deploying to Nexus server'
                sh 'mvn deploy'
            }
        }

        stage('Email Notification') {
                    steps {
                        mail bcc: '',
                             body: '''Stage: GIT Pull
                                    - Pulling from Git...

                                    Stage: Maven Clean Compile
                                    - Building Spring project...

                                    Stage: Test - JUNIT/MOCKITO
                                    - Testing Spring project...

                                    Stage: JaCoCo Report
                                    - Generating JaCoCo Coverage Report...

                                    Stage: SonarQube Analysis
                                    - Running Sonarqube analysis...

                                    Stage: Deploy to Nexus
                                    - Deploying to Nexus...

                                    Stage: Build Docker Image
                                    - Building Docker image for the application...

                                    Stage: Push Docker Image
                                    - Pushing Docker image to Docker Hub...

                                    Stage: Docker Compose
                                    - Running Docker Compose...

                                    Stage: Monitoring Services G/P
                                    - Starting Prometheus and Grafana...

                                    Final Report: The pipeline has completed successfully. No action required''',
                             cc: '',
                             from: '',
                             replyTo: '',
                             subject: 'Pipeline build status',
                             to: 'slim.drissi@esprit.tn'
                    }
                }
    }
}
