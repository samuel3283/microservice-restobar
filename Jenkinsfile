pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/develop']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'snavarro3283', url: 'https://snavarro3283@bitbucket.org/snavarro3283/restobar.git']]])
            }
        }        
        stage('Build') {
            steps {
                echo 'Building..'
				bat 'mvn clean install' 
				//sh 'mvn -B -DskipTests clean install'   	|
            }
        }
        stage('Verify Coverage') {
            steps {
                //Si quieres ver la cobertura en sonar es necesario ejecutar cobertura y después sonar
				//Compilación y ejecución de tests con Maven usando Jacoco:
                //bat "mvn -B --batch-mode -V -U -e org.jacoco:jacoco-maven-plugin:prepare-agent install -Dmaven.test.failure.ignore=true"
                bat "mvn -B --batch-mode -V -U -e sonar:sonar"
                echo 'Sonarqube Analysis'
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
				bat "mvn -B --batch-mode -V -U -e deploy"
            }
        }
    }
}