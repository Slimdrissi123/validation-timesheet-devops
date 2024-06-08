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




        /*stage('docker-compose.yaml'){
                   steps{
                          sh 'docker-compose up -d'


                            }
                        }*/
        stage('Dockerfile'){

        steps{
                          sh "docker login -u ihebmouhligh -p Iheb2000@"
                          sh "docker push ihebmouligh/timesheet-devops:1.0.0"
                              }
                                  }



    }
}