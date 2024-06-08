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
                sh 'mvn test'
            }
        }
        /*stage('SonarQube analysis') {
            steps {
                sh 'mvn sonar:sonar \
                    -Dsonar.projectKey=Firas_Fejjeri \
                    -Dsonar.host.url=http://192.168.50.4:9000 \
                    -Dsonar.login=5ce3852b5c3fe5a0ff9f674d4cfc68c8e472fe93 '
            }
        }*/
        stage('Docker Build') {
                    steps {
                        script {
                            docker.build("firasfejjeri/timesheet-devops:1.0")
                        }
                    }
                }
        stage('Docker Push') {
                    steps {
                        withCredentials([usernamePassword(credentialsId: 'dockerHubCredentials', usernameVariable: 'DOCKER_HUB_USERNAME', passwordVariable: 'DOCKER_HUB_PASSWORD')]) {
                            sh "docker login -u $DOCKER_HUB_USERNAME -p $DOCKER_HUB_PASSWORD"
                            sh "docker push firasfejjeri/timesheet-devops:1.0"
                        }
                    }
                }
        stage('Docker compose'){
            steps{
                sh '/usr/libexec/docker/cli-plugins/docker-compose up -d'
            }
        }

        stage('Deploy to Nexus') {
                    steps {
                     sh 'mvn deploy'
                }
        }
    }