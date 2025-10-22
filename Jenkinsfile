pipeline {
    agent any

    tools {
        jdk 'jdk17'          // Must match the JDK name configured in Jenkins
        maven 'Maven3'       // Make sure Maven3 is configured in Jenkins
    }

    environment {
        SONARQUBE = 'sonarqube'   // The name you used in Jenkins > Manage Jenkins > System (SonarQube servers)
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/bellaaghaaziz/order-management-devops.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh '''
                        mvn sonar:sonar \
                        -Dsonar.projectKey=order-management \
                        -Dsonar.host.url=http://192.168.56.10:9001 \
                        -Dsonar.login=$SONAR_AUTH_TOKEN
                    '''
                }
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }
    }
}

