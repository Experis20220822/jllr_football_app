package services
import models.{Player, Position}

import scala.collection.mutable.ListBuffer
import scala.util.Try

class MemoryPlayerService extends PlayerService {
  val mutableList:ListBuffer[Player] = ListBuffer.empty

  override def create(player: Player): Unit = mutableList += player

  override def update(player: Player): Try[Player] = {
    Try(mutableList.find(p => p.id == player.id).head)
      .map(p => {
        mutableList.filterInPlace(p => p.id != player.id).addOne(player)
        p
      })
  }

  override def findById(id: Long): Option[Player] = mutableList.find(p => id == p.id)

  override def findAll(): List[Player] = mutableList.toList

  override def findByFirstName(firstName: String): Option[Player] =
    mutableList.find(p => p.firstName == firstName)

  override def findByLastName(lastName: String): Option[Player] =
    mutableList.find(p => p.lastName == lastName)


  override def findByPosition(position: Position): List[Player] = mutableList.filter(p => p.position == position).toList
}
