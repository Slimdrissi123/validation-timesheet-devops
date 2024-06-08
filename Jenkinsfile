pipeline {
    agent any
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
        }
    }