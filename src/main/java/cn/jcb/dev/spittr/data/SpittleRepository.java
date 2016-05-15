package cn.jcb.dev.spittr.data;

import java.util.List;

import cn.jcb.dev.spittr.domain.Spittle;

public interface SpittleRepository {
	List<Spittle> findSpittles(long max, int count);

	Spittle findOne(long spittleId);
}
