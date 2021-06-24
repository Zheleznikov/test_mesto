pipeline {
    agent {
        docker {
            image 'maven:3.8.1-adoptopenjdk-11'
            args '-v $HOME/.m2:/root/.m2'
        }
    }
    stages {
        stage('build') {
            steps {
                echo 'building the application...'
                sh 'mvn --version'
                sh 'mvn clean install'
            }
        }

        stage("test") {
            steps {
                echo 'run tests...'
                sh 'mvn test'
            }
        }
//
//         stage("deploy") {
//             steps {
//                 echo 'deploying the application...'
//
//             }
//         }
    }
}