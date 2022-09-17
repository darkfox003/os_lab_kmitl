#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>

int	csum;
void	*runner(void *param);

int	main(int argc, char **argv)
{
	pthread_t	tid;
	pthread_attr_t	attr;
	int	msum;

	pthread_attr_init(&attr);
	pthread_create(&tid, &attr, runner, argv[1]);
	msum = 0;
	for (int i=1; i<=atoi(argv[1]); i++)
		msum += i;
	printf("csum - msum = %d\n", csum - msum);
	return (0);

	/*
	Output: ./a.out 4
		csum - msum = -10
		csum = msum = 26
	 */
}

void	*runner(void *parem)
{
	int	upper = atoi(parem);
	int	i;
	csum = 0;
	if (upper > 0)
	{
		for(i = 0; i <= 2 * upper; i++)
			csum += i;
	}
	pthread_exit(0);
}
