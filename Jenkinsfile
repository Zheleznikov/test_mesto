pipeline {
    agent {
        docker { image 'maven:3.8.1-adoptopenjdk-11' }
    }
    stages {
        stage('Test') {
            steps {
                sh 'mvn --version'
            }
        }
    }
}
