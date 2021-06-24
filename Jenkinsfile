pipeline {
//     agent {
//         docker {
//             image 'maven:3.8.1-adoptopenjdk-11'
//             args '-v $HOME/.m2:/root/.m2'
//         }
//     } 
    agent any
    stages {
        stage('Stage 1') {
            steps {
                echo 'Hello world!' 
                sh 'mvn --version'
            }
        }
    }
}
