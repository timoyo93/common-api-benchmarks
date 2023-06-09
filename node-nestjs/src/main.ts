import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import * as notReallyCluster from 'cluster';
const cluster = notReallyCluster as unknown as notReallyCluster.Cluster;

import * as os from 'os';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);
  if (cluster?.isPrimary) {
    const numWorkers = os.cpus().length * 2;

    for (let i = 0; i < numWorkers; i++) {
      cluster.fork();
    }

    cluster.on('exit', (worker, code, signal) => {
      console.log(`Worker ${worker.process.pid} died. Restarting...`);
      cluster.fork();
    });
  } else {
    await app.listen(3000);
  }
}

bootstrap();
