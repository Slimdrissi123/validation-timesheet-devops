pipeline {
    agent any
    tools {
        maven 'M2_HOME' // Assumes Maven is installed and named 'M2_HOME' in Jenkins global tool configuration
    }
    stages {
        stage('Compile') {

            steps {
               
                sh 'mvn compile'
            }
        }
        stage('Tests - JUnit/Mockito') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Rapport JaCoCo') {
            steps {
                sh 'mvn test'
                sh 'mvn jacoco:report'
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

        stage('SonarQube analysis') {
            steps {
                

                  sh ''' mvn sonar:sonar \
                        -Dsonar.projectKey=timsheet-devops\
                        -Dsonar.host.url=http://192.168.56.2:9000\
                        -Dsonar.login=654ace06b219a3a7c1f7c0e8dfa66bf7a8380867'''
            }
        }
        stage('Deploy to nexus'){
            steps
                {
                    echo 'Deploying to nexus server'
                    sh 'mvn deploy'
                }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t mbedir/timesheet-devops:1.0.0 .'
                }
            }
        }
      stage('Push Docker Image to DockerHub') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'dockerhub_account', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                    
                    sh "docker login -u ${DOCKERHUB_USERNAME} -p ${DOCKERHUB_PASSWORD}"
                    sh "docker push mbedir/timesheet-devops:1.0.0"
                    
                } 
            }
        }
        }
        stage('Integration Tests') {
            steps {
                sh 'mvn integration-test'
            }
        }
        stage('Docker compose (FrontEnd BackEnd MySql)') {
            steps {
                script {
                   sh '''
                    docker-compose down -v
                    docker-compose up -d
                    '''
                }
            }
        }
         stage('Test Email') {
            steps {
                emailext (
                    subject: "Sending Email from Jenkins Pipeline",
                    body: "Pipeline DONNEEEEEEEE :).",
                    to: "bedir.malek@esprit.tn"


                )
            }
        }
        
    }
    

}
