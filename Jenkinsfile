pipeline {
    agent any
    stages {
        stage('compile'){
            steps{
                sh 'mvn clean install'
            }
        }
        stage('sonar'){
            steps{
                 sh '''
                    mvn sonar:sonar \
                      -Dsonar.projectKey=validation-devops \
                      -Dsonar.host.url=http://192.168.50.4:9000 \
                      -Dsonar.login=e774bbec6027322df760abafd6c0a144e15fc7e2
                      '''
                    }
        }
        stage('nexus'){
                    steps{
                        sh 'mvn deploy'
                    }
                }
         stage('Build Docker Image') {
                    steps {
                        script {
                            docker.build("ihebmouligh/timesheet-devops:1.0.0")
                        }
                    }
                }

                stage('Push Docker Image') {
                    steps {
                        script {
                            docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-credentials') {
                                docker.image("ihebmouligh/timesheet-devops:1.0.0").push()
                            }
                        }



        stage('docker-compose.yaml'){
                   steps{
                               sh 'docker-compose up -d'


                            }
                        }
        stage('Dockerfile'){
                                           steps{
                                                        sh "docker login -u ihebmouhligh -p Iheb2000@"
                                                        sh "docker push ihebmouligh/timesheet-devops:1.0.0"
                                                    }
                                                }



    }
}