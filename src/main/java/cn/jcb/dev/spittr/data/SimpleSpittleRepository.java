package cn.jcb.dev.spittr.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import cn.jcb.dev.spittr.domain.Spittle;

@Component
public class SimpleSpittleRepository implements SpittleRepository {
	private List<Spittle> spittles;
	
	@Override
	public List<Spittle> findSpittles(long max, int count) {
		spittles = new ArrayList<Spittle>();
		for (int i = 0; i < count; i++) {
			spittles.add( new Spittle(Long.valueOf(i), "Spittle " + i, new Date()) );
		}
		return spittles;
	}

	@Override
	public Spittle findOne(long spittleId) {
		if (spittles==null)
			return null;

		for(Spittle sp : spittles){
			if (sp.getId() == spittleId)
				return sp;
		}
		
		return null;
	}

}
